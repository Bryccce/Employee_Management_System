package edu.bw.web.user;

import cn.hutool.json.JSONUtil;
import edu.bw.common.CommonResult;
import edu.bw.dto.InsertUserForm;
import edu.bw.service.Impl.UserServiceImpl;
import edu.bw.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/user/insert")
public class UserInsert extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reqJSON = request.getReader().readLine();
        InsertUserForm insertUserForm = JSONUtil.toBean(reqJSON, InsertUserForm.class);

        //2.调用service处理
        Integer rows = userService.insert(insertUserForm);

        //3.将数据响应给前端
        response.getWriter().write(JSONUtil.toJsonStr(CommonResult.ok().put("rows", rows)));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
