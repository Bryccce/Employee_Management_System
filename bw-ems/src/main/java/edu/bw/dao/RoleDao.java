package edu.bw.dao;

import edu.bw.dto.InsertRoleForm;
import edu.bw.dto.SelectRoleByConditionForm;
import edu.bw.dto.UpdateRoleForm;
import edu.bw.pojo.Role;
import edu.bw.web.role.RoleUpdate;

import java.util.List;
import java.util.Map;

public interface RoleDao {
    List<Map<String, Object>> selectRoleByCondition(SelectRoleByConditionForm bean);

    Long countSelectRoleByCondition(SelectRoleByConditionForm bean);

    Integer insert(Role role);

    List<Map<String, Object>> selectRoleById(Integer id);

    Integer update(UpdateRoleForm bean);

    boolean countDeleteRoleByIds(List<Integer> list);

    int deleteRoleByIds(List<Integer> list);
}
