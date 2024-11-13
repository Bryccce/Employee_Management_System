package edu.bw.service;

import edu.bw.dto.SelectDeptByConditionForm;
import edu.bw.dto.UpdateDeptForm;
import edu.bw.pojo.Dept;
import edu.bw.utils.PageUtils;

import java.util.List;
import java.util.Map;

public interface DeptService {
    PageUtils selectDeptByCondition(SelectDeptByConditionForm bean);

    Integer insert(Dept bean);

    List<Map<String, Object>> selectBlyd(Integer id);

    Integer update(UpdateDeptForm bean);
}
