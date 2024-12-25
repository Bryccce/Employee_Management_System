package edu.bw.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import edu.bw.dao.UserDao;
import edu.bw.dao.impl.UserDaoImpl;
import edu.bw.dto.InsertUserForm;
import edu.bw.dto.SelectUserByConditionForm;
import edu.bw.dto.UpdateUserForm;
import edu.bw.pojo.User;
import edu.bw.service.UserService;
import edu.bw.utils.PageUtils;
import edu.bw.web.user.SelectUserByCondition;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    UserDao userDao = new UserDaoImpl();//Service中所有的方法都能够使用userDao

    @Override
    public Integer userLogin(User user) throws SQLException {
        return userDao.userLogin(user);
    }

    @Override
    public PageUtils selectUserByCondition(SelectUserByConditionForm bean) {
        List<Map<String, Object>> list = userDao.selectUserByCondition(bean);
        int start = (bean.getPage() - 1) * bean.getLength();
        long count = userDao.selectUserByConditionCount(bean);
        return new PageUtils(list, count, start, bean.getLength());
    }

    @Override
    public Integer insert(InsertUserForm insertUserForm) {
        User user = new User();
        BeanUtil.copyProperties(insertUserForm, user);
        return userDao.insert(user);
    }

    @Override
    public List<Map<String, Object>> selectById(Integer userId) {
        return userDao.selectById(userId);
    }

    @Override
    public Integer update(UpdateUserForm updateUserForm) {
        User user = new User();
        BeanUtil.copyProperties(updateUserForm, user);
        user.setId(updateUserForm.getUserId());
        return userDao.update(user);
    }

    @Override
    public Integer deleteByIds(List ids) {
        return userDao.deleteByIds(ids);
    }
}
