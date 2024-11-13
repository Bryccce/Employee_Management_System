package edu.bw.web.dept;

import cn.hutool.json.JSONUtil;
import edu.bw.common.CommonResult;
import edu.bw.dto.UpdateDeptForm;
import edu.bw.service.DeptService;
import edu.bw.service.Impl.DeptServiceImp;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/dept/selectById")
public class DeptSelectByldServlet extends HttpServlet {
    DeptService service = new DeptServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getReader().readLine();
        UpdateDeptForm bean = JSONUtil.toBean(s, UpdateDeptForm.class);
        List<Map<String, Object>> list = service.selectBlyd(bean.getId());
        response.getWriter().write(JSONUtil.toJsonStr(CommonResult.ok(list.get(0))));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
