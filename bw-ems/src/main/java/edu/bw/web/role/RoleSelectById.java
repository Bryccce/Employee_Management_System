package edu.bw.web.role;

import cn.hutool.json.JSONUtil;
import edu.bw.common.CommonResult;
import edu.bw.pojo.Role;
import edu.bw.service.Impl.RoleServiceImp;
import edu.bw.service.RoleService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/role/selectById")
public class RoleSelectById extends HttpServlet {
    RoleService roleService = new RoleServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reqJSON = request.getReader().readLine();
        Role bean = JSONUtil.toBean(reqJSON, Role.class);
        List<Map<String, Object>> list = roleService.selectById(bean);
        response.getWriter().write(JSONUtil.toJsonStr(CommonResult.ok(list.get(0))));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
