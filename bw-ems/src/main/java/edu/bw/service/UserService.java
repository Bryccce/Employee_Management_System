package edu.bw.service;

import edu.bw.pojo.User;

import java.sql.SQLException;

public interface UserService {
    Integer userLogin(User user) throws SQLException;
}
