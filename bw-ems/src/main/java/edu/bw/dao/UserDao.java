package edu.bw.dao;

import edu.bw.dto.SelectUserByConditionForm;
import edu.bw.pojo.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface UserDao {
    /**
     * @return
     */
    public Integer userLogin(User user);

    List<Map<String, Object>> selectUserByCondition(SelectUserByConditionForm bean);

    long selectUserByConditionCount(SelectUserByConditionForm bean);

    Integer insert(User user);

    List<Map<String, Object>> selectById(Integer userId);

    Integer update(User user);

    Integer deleteByIds(List ids);
}
