package edu.bw.web.dept;

import cn.hutool.json.JSONUtil;
import edu.bw.common.CommonResult;
import edu.bw.dto.SelectDeptByConditionForm;
import edu.bw.service.DeptService;
import edu.bw.service.Impl.DeptServiceImp;
import edu.bw.utils.JDBCUtils;
import edu.bw.utils.PageUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/dept/selectDeptByCondition")
public class SelectDeptByConditionServlet extends HttpServlet {
    DeptService service = new DeptServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reqJSON = request.getReader().readLine();
        SelectDeptByConditionForm bean = JSONUtil.toBean(reqJSON, SelectDeptByConditionForm.class);
        PageUtils pageUtils = service.selectDeptByCondition(bean);
        response.getWriter().write(JSONUtil.toJsonStr(CommonResult.ok().put("page", pageUtils)));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
