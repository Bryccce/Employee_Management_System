package edu.bw.dao.impl;

import edu.bw.dao.UserDao;
import edu.bw.dto.SelectUserByConditionForm;
import edu.bw.pojo.User;
import edu.bw.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserDaoImpl implements UserDao {
    QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());

    /**
     * SELECT id
     * FROM bw_zzy_users
     * WHERE username = 'zhangsan'
     * AND `password`= HEX(AES_ENCRYPT('zhangsan','zhangsan'))
     *
     * param user
     * @return
     */
    @Override
    public Integer userLogin(User user) {
        try {
            return qr.query("SELECT id FROM bw_zzy_users WHERE username = ?" +
                            "AND `password`= HEX(AES_ENCRYPT(?,?))", new ScalarHandler<Integer>(),
                    user.getUsername(),user.getPassword(),user.getUsername());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Map<String, Object>> selectUserByCondition(SelectUserByConditionForm params) {
        StringBuilder sql = new StringBuilder("SELECT DISTINCT u.id, u.name, u.sex, u.tel, u.email, d.dept_name AS dept, ");
        sql.append("u.hiredate, u.root, u.status, ")
                .append("(SELECT GROUP_CONCAT(role_name separator '，') FROM bw_zzy_role ")
                .append("WHERE JSON_CONTAINS(u.role, CONVERT(id, CHAR))) AS roles ")
                .append("FROM bw_zzy_users u ")
                .append("LEFT JOIN bw_zzy_role r ON JSON_CONTAINS(u.role, CONVERT(r.id, CHAR)) ")
                .append("LEFT JOIN bw_zzy_dept d ON u.dept_id = d.id ")
                .append("WHERE 1=1 ");
        List<Object> queryParams = new ArrayList<>();

        if (params.getName() != null && !params.getName().isEmpty()) {
            sql.append("AND u.name LIKE ? ");
            queryParams.add("%" + params.getName() + "%");
        }
        if (params.getSex() != null && !params.getSex().isEmpty()) {
            sql.append("AND u.sex = ? ");
            queryParams.add(params.getSex());
        }
        if (params.getRole() != null && !params.getRole().isEmpty()) {
            sql.append("AND r.role_name = ? ");
            queryParams.add(params.getRole());
        }
        if (params.getDeptId() != null) {
            sql.append("AND d.id = ? ");
            queryParams.add(params.getDeptId());
        }
        if (params.getStatus() != null) {
            sql.append("AND u.status = ? ");
            queryParams.add(params.getStatus());
        }

        sql.append("LIMIT ?, ?");
        queryParams.add((params.getPage() - 1) * params.getLength());
        queryParams.add(params.getLength());

        try {
            return qr.query(sql.toString(), new MapListHandler(), queryParams.toArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public long selectUserByConditionCount(SelectUserByConditionForm params) {
        StringBuilder sql = new StringBuilder("SELECT count(distinct u.id) ")
                .append("FROM bw_zzy_users u ")
                .append("JOIN bw_zzy_role r ON JSON_CONTAINS(u.role, CONVERT(r.id, CHAR)) ")
                .append("LEFT JOIN bw_zzy_dept d ON u.dept_id = d.id ")
                .append("WHERE 1=1 ");

        List<Object> queryParams = new ArrayList<>();

        if (params.getName() != null && !params.getName().isEmpty()) {
            sql.append("AND u.name LIKE ? ");
            queryParams.add("%" + params.getName() + "%");
        }

        if (params.getSex() != null && !params.getSex().isEmpty()) {
            sql.append("AND u.sex = ? ");
            queryParams.add(params.getSex());
        }

        if (params.getRole() != null && !params.getRole().isEmpty()) {
            sql.append("AND r.role_name = ? ");
            queryParams.add(params.getRole());
        }

        if (params.getDeptId() != null) {
            sql.append("AND d.id = ? ");
            queryParams.add(params.getDeptId());
        }

        if (params.getStatus() != null) {
            sql.append("AND u.status = ? ");
            queryParams.add(params.getStatus());
        }

        try {
            return qr.query(sql.toString(), new ScalarHandler<Long>(), queryParams.toArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer insert(User user) {
        StringBuilder sql = new StringBuilder(" INSERT INTO bw_zzy_users SET ");
        List<Object> insertParams = new ArrayList<>();
        try {
            //利用反射设置sql和值
            for (Field declaredField : user.getClass().getDeclaredFields()) {
                declaredField.setAccessible(true);
                if (declaredField.get(user)!=null && !declaredField.get(user).equals("")) {
                    if (declaredField.getName().equals("password")) {
                        sql.append(" `password` = HEX(AES_ENCRYPT(?,?)), ");

                        Field usernameField = user.getClass().getDeclaredField("username");
                        usernameField.setAccessible(true);

                        insertParams.add(declaredField.get(user));
                        insertParams.add(usernameField.get(user));
                    } else if (declaredField.getName().equals("deptId")) {
                        sql.append(" dept_id = ?, ");
                        insertParams.add(declaredField.get(user));
                    }else if (declaredField.getName().equals("openId")) {
                        sql.append(" open_id = ?, ");
                        insertParams.add(declaredField.get(user));
                    }else{
                        //属性名和数据库的列名相同
                        sql.append(declaredField.getName() + " = ?, ");
                        insertParams.add(declaredField.get(user));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //INSERT INTO bw_zzy_users SET dept_id= ? , open_id=?, name=?,  .... photo=?,
        //sql.toString().replaceAll(",\\s*$","") 去掉sql末尾的,空格
        try {
            return qr.update(sql.toString().replaceAll(",\\s*$",""),
                    insertParams.toArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Map<String, Object>> selectById(Integer userId) {
        try {
            return qr.query("SELECT id, username, `name`, sex, tel, email, hiredate, role, dept_id AS deptId, status,\n" +
                    "CONVERT(AES_DECRYPT(UNHEX(`password`),username),CHAR) AS password\n" +
                    "FROM bw_zzy_users \n" +
                    "WHERE id = ?", new MapListHandler(), userId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer update(User user) {
        StringBuilder sql = new StringBuilder(" UPDATE bw_zzy_users SET ");
        List<Object> updateParams = new ArrayList<>();

        try {
            //利用反射设置sql和值
            for (Field declaredField : user.getClass().getDeclaredFields()) {
                declaredField.setAccessible(true);
                if (declaredField.get(user)!=null && !declaredField.get(user).equals("")) {
                    if (declaredField.getName().equals("password")) {
                        sql.append(" `password` = HEX(AES_ENCRYPT(?,?)), ");

                        Field usernameField = user.getClass().getDeclaredField("username");
                        usernameField.setAccessible(true);

                        updateParams.add(declaredField.get(user));
                        updateParams.add(usernameField.get(user));
                    } else if (declaredField.getName().equals("deptId")) {
                        sql.append(" dept_id = ?, ");
                        updateParams.add(declaredField.get(user));
                    }else if (declaredField.getName().equals("openId")) {
                        sql.append(" open_id = ?, ");
                        updateParams.add(declaredField.get(user));
                    }else{
                        //属性名和数据库的列名相同
                        sql.append(declaredField.getName() + " = ?, ");
                        updateParams.add(declaredField.get(user));
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        sql.append(" WHERE id = ? ");
        updateParams.add(user.getId());

        //UPDATE bw_zzy_users SET username= ?,  password=?, ... photo=?, WHERE id = ?
        //UPDATE bw_zzy_users SET username= ?,  password=?, ... photo=? WHERE id = ?
        try {
            return qr.update(sql.toString().replaceAll(",\\s*WHERE", " WHERE")
                    , updateParams.toArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer deleteByIds(List ids) {
        StringBuilder sql = new StringBuilder("DELETE FROM \n" +
                "bw_zzy_users\n" +
                "WHERE id in (");
        sql.append(ids.stream().map(id -> "?").collect(Collectors.joining(", ")))
                .append(")");

        try {
            return qr.update(sql.toString(), ids.toArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
