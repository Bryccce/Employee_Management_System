package edu.bw.web.dept;

import cn.hutool.json.JSONUtil;
import edu.bw.common.CommonResult;
import edu.bw.pojo.Dept;
import edu.bw.service.DeptService;
import edu.bw.service.Impl.DeptServiceImp;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/dept/insert")
public class InsertDeptServlet extends HttpServlet {
    DeptService service = new DeptServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reqJSON = request.getReader().readLine();
        Dept dept = JSONUtil.toBean(reqJSON, Dept.class);
        Integer rows = service.insert(dept);//rows代表插入了多少条数据
        response.getWriter().write(JSONUtil.toJsonStr(CommonResult.ok().put("rows", rows)));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
