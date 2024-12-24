package edu.bw.web.role;

import cn.hutool.db.Page;
import cn.hutool.json.JSONUtil;
import edu.bw.common.CommonResult;
import edu.bw.dto.SelectRoleByConditionForm;
import edu.bw.service.Impl.RoleServiceImp;
import edu.bw.service.RoleService;
import edu.bw.utils.PageUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/role/selectRoleByCondition")
public class SelectRoleByCondition extends HttpServlet {
    RoleService roleService = new RoleServiceImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reqJSON = request.getReader().readLine();
        SelectRoleByConditionForm bean = JSONUtil.toBean(reqJSON, SelectRoleByConditionForm.class);
        PageUtils pageUtils = roleService.selectRoleByCondition(bean);
        response.getWriter().write(JSONUtil.toJsonStr(CommonResult.ok().put("page", pageUtils)));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
