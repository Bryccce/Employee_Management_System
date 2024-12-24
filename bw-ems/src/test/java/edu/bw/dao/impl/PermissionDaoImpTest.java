package edu.bw.dao.impl;

import cn.hutool.json.JSONUtil;
import edu.bw.dao.PermissionDao;
import org.junit.Test;

import static org.junit.Assert.*;

public class PermissionDaoImpTest {
    PermissionDao pd = new PermissionDaoImp();
    @Test
    public void selectAllPermission() {
        System.out.println(JSONUtil.toJsonPrettyStr(pd.selectAllPermission()));
    }
}