package edu.bw.dao;

import edu.bw.dto.SelectDeptByConditionForm;
import edu.bw.dto.UpdateDeptForm;
import edu.bw.pojo.Dept;
import edu.bw.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface DeptDao {
    List selectDeptByCondition(SelectDeptByConditionForm bean);

    Long selectDeptByConditionCount(SelectDeptByConditionForm bean);

    Integer insert(Dept bean);

    List<Map<String, Object>> selectBlyd(Integer id);

    Integer update(UpdateDeptForm bean);

    boolean selectCanDelete(List<Integer> list);

    Integer deleteDeptByIds(List<Integer> list);
}
