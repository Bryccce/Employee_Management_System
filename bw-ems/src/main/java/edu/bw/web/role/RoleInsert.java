package edu.bw.web.role;

import cn.hutool.json.JSONUtil;
import edu.bw.common.CommonResult;
import edu.bw.dto.InsertRoleForm;
import edu.bw.service.Impl.RoleServiceImp;
import edu.bw.service.RoleService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/role/insert")
public class RoleInsert extends HttpServlet {
    RoleService rs = new RoleServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getReader().readLine();
        InsertRoleForm bean = JSONUtil.toBean(s, InsertRoleForm.class);
        Integer row = rs.insert(bean);
        response.getWriter().write(JSONUtil.toJsonStr(CommonResult.ok().put("rows", row)));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
