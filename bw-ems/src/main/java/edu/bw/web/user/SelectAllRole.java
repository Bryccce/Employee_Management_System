package edu.bw.web.user;

import cn.hutool.json.JSONUtil;
import edu.bw.common.CommonResult;
import edu.bw.service.Impl.RoleServiceImp;
import edu.bw.service.RoleService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/role/selectAllRole")
public class SelectAllRole extends HttpServlet {
    RoleService roleService = new RoleServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map<String, Object>> list=roleService.selectAllRole();
        response.getWriter().write(JSONUtil.toJsonStr(CommonResult.ok().put("list", list)));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
