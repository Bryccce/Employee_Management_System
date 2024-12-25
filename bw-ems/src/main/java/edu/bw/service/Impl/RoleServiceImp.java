package edu.bw.service.Impl;

import edu.bw.dao.RoleDao;
import edu.bw.dao.impl.RoleRaoImpl;
import edu.bw.dto.InsertRoleForm;
import edu.bw.dto.SelectRoleByConditionForm;
import edu.bw.dto.UpdateRoleForm;
import edu.bw.pojo.Role;
import edu.bw.service.RoleService;
import edu.bw.utils.PageUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class RoleServiceImp implements RoleService {
    RoleDao roleDao = new RoleRaoImpl();
    @Override
    public PageUtils selectRoleByCondition(SelectRoleByConditionForm bean) {
        List<Map<String, Object>> list = roleDao.selectRoleByCondition(bean);
        Long count = roleDao.countSelectRoleByCondition(bean);
        int beginIndex = (bean.getPage() - 1) * bean.getLength();
        return new PageUtils(list, count, beginIndex, bean.getLength());
    }

    @Override
    public Integer insert(InsertRoleForm bean) {
        Role role = new Role();
        role.setRoleName(bean.getRoleName());
        role.setPermissions(Arrays.toString(bean.getPermissions()));
        role.setDesc(bean.getDesc());
        return roleDao.insert(role);
    }

    @Override
    public List<Map<String, Object>> selectById(Role bean) {
        return roleDao.selectRoleById(bean.getId());
    }

    @Override
    public Integer update(UpdateRoleForm bean) {
        return  roleDao.update(bean);
    }

    @Override
    public int deleteRoleByIds(List<Integer> list) {
        int rows = 0;
        if(roleDao.countDeleteRoleByIds(list)){
            return roleDao.deleteRoleByIds(list);
        }
        return rows;
    }

    @Override
    public List<Map<String, Object>> selectAllRole() {
        return roleDao.selectAllRole();
    }
}
