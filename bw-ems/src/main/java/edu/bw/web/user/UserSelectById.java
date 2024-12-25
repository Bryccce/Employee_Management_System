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
import java.util.Map;

@WebServlet("/user/selectById")
public class UserSelectById extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reqJSON = request.getReader().readLine();
        Integer userId = JSONUtil.parseObj(reqJSON).get("userId", Integer.class);
        List<Map<String, Object>> list = userService.selectById(userId);
        response.getWriter().write(JSONUtil.toJsonStr(CommonResult.ok(list.get(0))));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
