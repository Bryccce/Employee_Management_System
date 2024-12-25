package edu.bw.web.dept;

import cn.hutool.json.JSONUtil;
import edu.bw.common.CommonResult;
import edu.bw.service.DeptService;
import edu.bw.service.Impl.DeptServiceImp;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/dept/selectAllDept")
public class SelectAllDept extends HttpServlet {
    DeptService deptService = new DeptServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map<String, Object>> list=deptService.selectAllDept();
        response.getWriter().write(JSONUtil.toJsonStr(CommonResult.ok().put("list", list)));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
