package edu.bw.dao;

import edu.bw.pojo.User;

import java.sql.SQLException;

public interface UserDao {
    /**
     * @return
     */
    public Integer userLogin(User user);
}
