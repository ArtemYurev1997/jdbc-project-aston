package org.aston.jdbc.controller.user;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.jdbc.contract.UserService;
import org.aston.jdbc.dto.UserResponse;
import org.aston.jdbc.mapper.UserMapper;

import java.io.IOException;

public class UserGetByIdServlet extends HttpServlet {
    private final UserService userService;
    private final UserMapper userMapper;

    @Inject
    public UserGetByIdServlet(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        var user = userService.findById(id);
        UserResponse userResponse = userMapper.toResponse(user);
        resp.getWriter().println(userResponse);
    }
}
