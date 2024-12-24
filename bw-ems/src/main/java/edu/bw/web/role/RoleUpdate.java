package edu.bw.web.role;

import cn.hutool.json.JSONUtil;
import edu.bw.common.CommonResult;
import edu.bw.dto.UpdateRoleForm;
import edu.bw.service.Impl.RoleServiceImp;
import edu.bw.service.RoleService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/role/update")
public class RoleUpdate extends HttpServlet {
    RoleService roleService = new RoleServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getReader().readLine();
        UpdateRoleForm bean = JSONUtil.toBean(s, UpdateRoleForm.class);
        Integer rows = roleService.update(bean);
        response.getWriter().write(JSONUtil.toJsonStr(CommonResult.ok().put("rows", rows)));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
