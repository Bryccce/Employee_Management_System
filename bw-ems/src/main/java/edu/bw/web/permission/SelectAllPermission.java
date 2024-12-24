package edu.bw.web.permission;

import cn.hutool.json.JSONUtil;
import edu.bw.common.CommonResult;
import edu.bw.service.Impl.SelectAllPermissionImp;
import edu.bw.service.PermissionService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/permission/selectAllPermission")
public class SelectAllPermission extends HttpServlet {
    PermissionService ps = new SelectAllPermissionImp();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Map<String, Object>> list = ps.selectAllPermission();
        response.getWriter().write(JSONUtil.toJsonStr(CommonResult.ok().put("list", list)));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
