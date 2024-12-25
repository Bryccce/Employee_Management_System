package edu.bw.dao.impl;

import edu.bw.dao.UserDao;
import edu.bw.dto.SelectUserByConditionForm;
import edu.bw.pojo.User;
import org.junit.Test;

import java.sql.SQLException;

public class UserDaoImplTest {
    UserDao userDao = new UserDaoImpl();

    @Test
    public void testUserLogin() {
        System.out.println(userDao.userLogin(new User("zhangsan", "zhangsan")));
    }


    @Test
    public void selectUserByCondition() {
    }

    @Test
    public void selectUserByConditionCount() {
        System.out.println(userDao.selectUserByConditionCount(new SelectUserByConditionForm()));
    }
}