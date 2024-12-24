package edu.bw.dao.impl;

import edu.bw.dao.RoleDao;
import edu.bw.dto.InsertRoleForm;
import edu.bw.dto.SelectRoleByConditionForm;
import edu.bw.dto.UpdateRoleForm;
import edu.bw.pojo.Role;
import edu.bw.utils.JDBCUtils;
import edu.bw.web.role.RoleUpdate;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RoleRaoImpl implements RoleDao {
    QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

    @Override
    public List<Map<String, Object>> selectRoleByCondition(SelectRoleByConditionForm bean){
        StringBuilder sql = new StringBuilder(" SELECT r.id, r.role_name AS roleName, COUNT(u.id) AS users, JSON_LENGTH(r.permissions) AS permissions, r.`desc`, r.systemic FROM bw_zzy_role r " +
                " LEFT JOIN bw_zzy_users u ON JSON_CONTAINS(u.role, CONVERT(r.id,CHAR)) WHERE 1=1 ");
        List<Object> queryParam = new ArrayList<>();
        if(bean.getRoleName() != null && !bean.getRoleName().isEmpty()){
            queryParam.add("%" + bean.getRoleName() + "%");
            sql.append(" AND r.role_name LIKE ? ");
        }
        sql.append(" GROUP BY r.id ORDER BY r.id LIMIT ?,?");
        queryParam.add((bean.getPage() - 1) * bean.getLength());
        queryParam.add(bean.getLength());
        try {
            return queryRunner.query(sql.toString(), new MapListHandler(), queryParam.toArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Long countSelectRoleByCondition(SelectRoleByConditionForm bean) {
        StringBuilder sql = new StringBuilder(" SELECT COUNT(*) FROM ( SELECT r.id FROM bw_zzy_role r LEFT JOIN bw_zzy_users u " +
                "ON JSON_CONTAINS(u.role, CONVERT(r.id,CHAR)) WHERE 1=1 ");

        List<Object> queryParams = new ArrayList<>();

        if (bean.getRoleName()!=null && !bean.getRoleName().isEmpty()) {
            sql.append(" AND r.role_name LIKE ? ");
            queryParams.add("%" + bean.getRoleName() + "%");
        }

        sql.append(" GROUP BY r.id ) AS temp");

        try {
            return queryRunner.query(sql.toString(),new ScalarHandler<>(),queryParams.toArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer insert(Role role) {
        StringBuilder sql = new StringBuilder("INSERT INTO bw_zzy_role SET role_name = ? , permissions = ? ");
        List<Object> insertParams = new ArrayList<>();
        insertParams.add(role.getRoleName());
        insertParams.add(role.getPermissions());
        if (role.getDesc()!=null && !role.getDesc().isEmpty()) {
            sql.append(" , `desc`= ? ");
            insertParams.add(role.getDesc());
        }
        try {
            return queryRunner.update(sql.toString(), insertParams.toArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Map<String, Object>> selectRoleById(Integer id) {
        StringBuilder sql = new StringBuilder("SELECT id,role_name AS roleName,permissions,`desc`,default_permissions AS defaultPermissions\n" +
                "FROM bw_zzy_role WHERE id=?");
        try {
            return queryRunner.query(sql.toString(), new MapListHandler(), id.toString());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Integer update(UpdateRoleForm bean) {
        List<Object> queryParams = new ArrayList<>();
        queryParams.add(bean.getRoleName());
        queryParams.add(bean.getDesc());
        queryParams.add(bean.getPermissions());
        queryParams.add(bean.getId());
        try {
            return queryRunner.update("UPDATE bw_zzy_role SET role_name=? , `desc` = ? , permissions = ? WHERE id=?",
                    queryParams.toArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean countDeleteRoleByIds(List<Integer> list) {
        StringBuilder sql = new StringBuilder("SELECT IF(SUM(temp.users)>0,FALSE,TRUE) FROM\n" +
                "(\n" +
                "SELECT COUNT(u.id) AS users\n" +
                "FROM bw_zzy_role r \n" +
                "INNER JOIN bw_zzy_users u ON JSON_CONTAINS(u.role, CONVERT(r.id,CHAR))\n" +
                "WHERE 1=1 AND r.id IN (");
        sql.append(list.stream().map(id -> "?").collect(Collectors.joining(", ")))//11,12 => ? ? => ?, ?
                .append(")\n" +
                        "\tGROUP BY r.id\n" +
                        ") temp");

        try {
            Long result = queryRunner.query(sql.toString(), new ScalarHandler<Long>(), list.toArray());
            return result == 1 ? true : false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteRoleByIds(List<Integer> list) {
        StringBuilder sql = new StringBuilder("DELETE FROM bw_zzy_role\n" +
                "WHERE id in (");
        sql.append(list.stream().map(id -> "?").collect(Collectors.joining(", ")))
                .append(")\n" +
                        "AND systemic = FALSE");

        try {
            return queryRunner.update(sql.toString(), list.toArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
