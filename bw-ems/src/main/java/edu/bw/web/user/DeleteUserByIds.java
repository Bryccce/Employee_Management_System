package edu.bw.web.user;

import cn.hutool.json.JSONUtil;
import edu.bw.common.CommonResult;
import edu.bw.service.Impl.UserServiceImpl;
import edu.bw.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/user/deleteUserByIds")
public class DeleteUserByIds extends HttpServlet {
    UserService us = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reqJSON = request.getReader().readLine();
        List<Integer> ids = JSONUtil.parseArray(JSONUtil.parseObj(reqJSON).get("ids")).toList(Integer.class);
        Integer rows = us.deleteByIds(ids);
        response.getWriter().write(JSONUtil.toJsonStr(CommonResult.ok().put("rows", rows)));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
