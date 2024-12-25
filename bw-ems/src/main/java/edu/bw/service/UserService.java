package edu.bw.service;

import edu.bw.dto.InsertUserForm;
import edu.bw.dto.SelectUserByConditionForm;
import edu.bw.dto.UpdateUserForm;
import edu.bw.pojo.User;
import edu.bw.utils.PageUtils;
import edu.bw.web.user.SelectUserByCondition;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserService {
    Integer userLogin(User user) throws SQLException;

    PageUtils selectUserByCondition(SelectUserByConditionForm bean);

    Integer insert(InsertUserForm insertUserForm);

    List<Map<String, Object>> selectById(Integer userId);

    Integer update(UpdateUserForm updateUserForm);

    Integer deleteByIds(List ids);
}
