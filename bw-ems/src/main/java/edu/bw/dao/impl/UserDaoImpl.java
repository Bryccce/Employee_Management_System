package edu.bw.dao.impl;

import edu.bw.dao.UserDao;
import edu.bw.pojo.User;
import edu.bw.utils.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;

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

}
