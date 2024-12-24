package edu.bw.dao.impl;

import cn.hutool.json.JSONUtil;
import edu.bw.dao.DeptDao;
import edu.bw.dto.SelectDeptByConditionForm;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DeptDaoImpTest extends TestCase {
    DeptDao dao = new DeptDaoImp();

    public void testSelectDeptByCondition() {
        List<Map<String, Object>> list = dao.selectDeptByCondition(new SelectDeptByConditionForm("后", 1, 10));
        System.out.println(JSONUtil.toJsonPrettyStr(list));
    }

    public void testSelectDeptByConditionCount() {
        System.out.println(dao.selectDeptByConditionCount(new SelectDeptByConditionForm("行", 1, 10)));

    }

    public void testSelectCanDelete() {
        System.out.println(dao.selectCanDelete(Arrays.asList(1, 3, 5)));
        System.out.println(dao.selectCanDelete(Arrays.asList(7, 8)));
    }
}