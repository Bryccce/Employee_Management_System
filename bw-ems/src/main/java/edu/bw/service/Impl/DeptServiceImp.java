package edu.bw.service.Impl;

import edu.bw.dao.DeptDao;
import edu.bw.dao.impl.DeptDaoImp;
import edu.bw.dto.SelectDeptByConditionForm;
import edu.bw.dto.UpdateDeptForm;
import edu.bw.pojo.Dept;
import edu.bw.service.DeptService;
import edu.bw.utils.PageUtils;

import java.util.List;
import java.util.Map;

public class DeptServiceImp implements DeptService {
    DeptDao dao = new DeptDaoImp();
    @Override
    public PageUtils selectDeptByCondition(SelectDeptByConditionForm bean) {
        List<Map<String, Object>> list = dao.selectDeptByCondition(bean);
        Long count = dao.selectDeptByConditionCount(bean);
        int beginIndex = (bean.getPage() - 1) * bean.getLength();
        return new PageUtils(list, count, beginIndex, bean.getLength());
    }

    @Override
    public Integer insert(Dept bean) {
        return dao.insert(bean);
    }

    @Override
    public List<Map<String, Object>> selectBlyd(Integer id) {
        return dao.selectBlyd(id);
    }

    @Override
    public Integer update(UpdateDeptForm bean) {
        return dao.update(bean);
    }

    @Override
    public Integer deleteDeptByIds(List<Integer> list) {
        Integer rows = 0;
        if(dao.selectCanDelete(list)){
            rows = dao.deleteDeptByIds(list);
        }
        return rows;
    }

    @Override
    public List<Map<String, Object>> selectAllDept() {
        return dao.selectAllDept();
    }
}
