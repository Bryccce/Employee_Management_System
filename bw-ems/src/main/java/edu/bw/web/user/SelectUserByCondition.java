package edu.bw.web.user;

import cn.hutool.json.JSONUtil;
import edu.bw.common.CommonResult;
import edu.bw.service.Impl.UserServiceImpl;
import edu.bw.service.UserService;
import edu.bw.utils.PageUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/user/selectUserByCondition")
public class SelectUserByCondition extends HttpServlet {
    UserService us = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String s = request.getReader().readLine();
        SelectUserByCondition bean = JSONUtil.toBean(s, SelectUserByCondition.class);
        PageUtils pageUtils = us.selectUserByCondition(bean);
        response.getWriter().write(JSONUtil.toJsonStr(CommonResult.ok().put("page", pageUtils)));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
