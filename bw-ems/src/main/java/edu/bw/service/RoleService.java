package edu.bw.service;

import edu.bw.dto.InsertRoleForm;
import edu.bw.dto.SelectRoleByConditionForm;
import edu.bw.dto.UpdateRoleForm;
import edu.bw.pojo.Role;
import edu.bw.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface RoleService {
    PageUtils selectRoleByCondition(SelectRoleByConditionForm bean);

    Integer insert(InsertRoleForm bean);

    List<Map<String, Object>> selectById(Role bean);

    Integer update(UpdateRoleForm bean);

    int deleteRoleByIds(List<Integer> list);

    List<Map<String, Object>> selectAllRole();
}
