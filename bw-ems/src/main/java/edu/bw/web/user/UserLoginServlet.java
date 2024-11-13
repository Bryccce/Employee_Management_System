package edu.bw.web.user;

import cn.hutool.json.JSONUtil;
import edu.bw.common.CommonResult;
import edu.bw.pojo.User;
import edu.bw.service.UserService;
import edu.bw.service.Impl.UserServiceImpl;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/user/login")
public class UserLoginServlet extends HttpServlet {

    UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.解析前端请求的json数据 {"username":"zhangsan","password":"zhangsan"},并封装成User对象
        String reqJSON = request.getReader().readLine(); //{"username":"zhangsan","password":"zhangsan"}
        User user = JSONUtil.toBean(reqJSON, User.class);

        //2.调用UserService处理登录业务逻辑
        Integer userId = null;
        try {
            userId = userService.userLogin(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //3.向前端响应结果
        CommonResult result = CommonResult.ok().put("result", (userId != null) ? "登录成功" : "登录失败");

        response.getWriter().write(JSONUtil.toJsonStr(result));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
