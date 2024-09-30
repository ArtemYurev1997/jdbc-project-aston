package org.aston.jdbc.controller.user;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.jdbc.contract.UserService;
import org.aston.jdbc.domain.User;
import org.aston.jdbc.dto.UserResponse;
import org.aston.jdbc.mapper.UserMapper;

import java.io.IOException;
import java.util.List;

public class UserGetAllServlet extends HttpServlet {
    private final UserService userService;
    private final UserMapper userMapper;

    @Inject
    public UserGetAllServlet(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<User> users = userService.getAllUsers();
        List<UserResponse> userResponseList = users.stream().map(userMapper::toResponse).toList();
        resp.getWriter().println(userResponseList);
    }
}
