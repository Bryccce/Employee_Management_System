package edu.bw.dao.impl;

import edu.bw.dao.DeptDao;
import edu.bw.dto.SelectDeptByConditionForm;
import edu.bw.dto.UpdateDeptForm;
import edu.bw.pojo.Dept;
import edu.bw.utils.JDBCUtils;
import edu.bw.utils.PageUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeptDaoImp implements DeptDao {
    QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
    @Override
    public List selectDeptByCondition(SelectDeptByConditionForm bean) {
        StringBuilder sql = new StringBuilder("SELECT d.id, d.dept_name AS deptName, d.tel, d.email, d.`desc`, COUNT(u.id) AS emps " +
                "FROM bw_zzy_dept d LEFT JOIN bw_zzy_users u ON d.id = u.dept_id AND u.`status` = 1 " +
                "WHERE 1 = 1 ");
        List<Object> qrParams = new ArrayList<>();
        if (bean.getDeptName() != null && !bean.getDeptName().isEmpty()) {
            sql.append("AND d.dept_name LIKE ? ");
            qrParams.add("%" + bean.getDeptName() + "%");
        }
        sql.append("GROUP BY d.id LIMIT ?, ?");
        qrParams.add((bean.getPage() - 1) * bean.getLength());
        qrParams.add(bean.getLength());

        try {
            return qr.query(sql.toString(), new MapListHandler(), qrParams.toArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * SELECT COUNT(*) FROM
     * (
     * SELECT d.id, d.dept_name AS deptName, d.tel, d.email, d.`desc`, COUNT(u.id) AS emps
     * FROM bw_zzy_dept d LEFT JOIN bw_zzy_users u ON d.id = u.dept_id AND u.`status` = 1
     * WHERE 1 = 1
     * GROUP BY d.id
     * ) AS temp
     * @param bean
     * @return
     */
    @Override
    public Long selectDeptByConditionCount(SelectDeptByConditionForm selectCondition) {
        //StringBuilder专门用来拼接sql语句
        StringBuilder sql = new StringBuilder(" SELECT COUNT(*) " +
                " FROM (SELECT d.id FROM bw_zzy_dept d LEFT JOIN bw_zzy_users u ON d.id = u.dept_id AND u.`status` = 1 " +
                " WHERE 1 = 1 ");

        //queryParams用来设置?参数值
        List<Object> queryParams = new ArrayList<>();

        if (selectCondition.getDeptName()!=null && !selectCondition.getDeptName().isEmpty()) {
            //此时拼接部门模糊查询
            sql.append(" AND d.dept_name LIKE ? ");
            queryParams.add("%" + selectCondition.getDeptName() + "%");
        }

        sql.append(" GROUP BY d.id) AS temp ");
        try {
            return qr.query(sql.toString(),new ScalarHandler<Long>(), queryParams.toArray());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * insert new department
     * @param bean
     * @return
     */
    @Override
    public Integer insert(Dept bean) {
        try {
            return qr.update("INSERT INTO bw_zzy_dept SET dept_name=?, tel=?, email=?, `desc`=?",
                    bean.getDeptName(), bean.getTel(), bean.getEmail(), bean.getDesc());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Map<String, Object>> selectBlyd(Integer id) {
        try {
            return qr.query("SELECT id, dept_name AS deptName, tel, email, `desc` FROM bw_zzy_dept WHERE id=?",
                    new MapListHandler(), id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Integer update(UpdateDeptForm bean) {
        try {
            return qr.update("UPDATE bw_zzy_dept SET dept_name=?, tel=?, email=?, `desc`=? WHERE id=?",
                    bean.getDeptName(), bean.getTel(), bean.getEmail(), bean.getDesc(), bean.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
