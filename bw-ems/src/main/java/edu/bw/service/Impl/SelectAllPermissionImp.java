package edu.bw.service.Impl;

import edu.bw.dao.PermissionDao;
import edu.bw.dao.impl.PermissionDaoImp;
import edu.bw.service.PermissionService;

import java.util.List;
import java.util.Map;

public class SelectAllPermissionImp implements PermissionService {
    PermissionDao pd = new PermissionDaoImp();
    @Override
    public List<Map<String, Object>> selectAllPermission() {
        return pd.selectAllPermission();
    }
}
