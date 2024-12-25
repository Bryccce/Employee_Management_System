package edu.bw.web.user;

import cn.hutool.json.JSONUtil;
import edu.bw.common.CommonResult;
import edu.bw.dto.UpdateUserForm;
import edu.bw.service.Impl.UserServiceImpl;
import edu.bw.service.UserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet("/user/update")
public class UserUpdate extends HttpServlet {
    UserService userService = new UserServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String reqJSON = request.getReader().readLine();
        UpdateUserForm updateUserForm = JSONUtil.toBean(reqJSON, UpdateUserForm.class);

        Integer rows = userService.update(updateUserForm);

        response.getWriter().write(JSONUtil.toJsonStr(CommonResult.ok().put("rows", rows)));
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
