package edu.bw.dao.impl;

import edu.bw.dao.PermissionDao;
import edu.bw.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PermissionDaoImp implements PermissionDao {
    QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
    @Override
    public List<Map<String, Object>> selectAllPermission() {
        try {
            return qr.query("SELECT p.id, m.module_name AS moduleName , a.action_name AS actionName\n" +
                    "FROM bw_zzy_permission p \n" +
                    "INNER JOIN bw_zzy_module m ON p.module_id = m.id\n" +
                    "INNER JOIN bw_zzy_action a ON p.action_id = a.id \n" +
                    "ORDER BY p.module_id, p.action_id;", new MapListHandler());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
