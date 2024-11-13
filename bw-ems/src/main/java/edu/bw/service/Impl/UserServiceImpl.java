package edu.bw.service.Impl;

import edu.bw.dao.UserDao;
import edu.bw.dao.impl.UserDaoImpl;
import edu.bw.pojo.User;
import edu.bw.service.UserService;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();//Service中所有的方法都能够使用userDao

    @Override
    public Integer userLogin(User user) throws SQLException {
        return userDao.userLogin(user);
    }
}
