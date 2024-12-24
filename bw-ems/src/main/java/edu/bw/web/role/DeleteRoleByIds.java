package edu.bw.web.role;

import cn.hutool.json.JSONUtil;
import edu.bw.common.CommonResult;
import edu.bw.service.Impl.RoleServiceImp;
import edu.bw.service.RoleService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/role/deleteRoleByIds")
public class DeleteRoleByIds extends HttpServlet {
    RoleService rs = new RoleServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Integer> list = JSONUtil.parseArray(JSONUtil.parseObj(request.getReader().readLine()).get("ids")).toList(Integer.class);
        int rows = rs.deleteRoleByIds(list);
        response.getWriter().write(JSONUtil.toJsonStr(CommonResult.ok().put("rows", rows)));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
