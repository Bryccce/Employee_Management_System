package edu.bw.service;

import edu.bw.dto.SelectUserByConditionForm;
import edu.bw.pojo.User;
import edu.bw.utils.PageUtils;
import edu.bw.web.user.SelectUserByCondition;

import java.sql.SQLException;

public interface UserService {
    Integer userLogin(User user) throws SQLException;

    PageUtils selectUserByCondition(SelectUserByConditionForm bean);
}
