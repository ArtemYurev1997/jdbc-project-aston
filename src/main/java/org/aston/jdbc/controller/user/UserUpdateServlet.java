package org.aston.jdbc.controller.user;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.jdbc.contract.UserService;
import org.aston.jdbc.dto.UserRequest;
import org.aston.jdbc.dto.UserResponse;
import org.aston.jdbc.enums.Role;
import org.aston.jdbc.mapper.UserMapper;

import java.io.IOException;

public class UserUpdateServlet extends HttpServlet {
    private final UserService userService;
    private final UserMapper userMapper;

    @Inject
    public UserUpdateServlet(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        UserRequest userRequest;
        if (req.getParameter("role").equals("Admin")) {
            userRequest = new UserRequest(name, surname, login, password, Role.ADMIN.getRole());
        } else {
            userRequest = new UserRequest(name, surname, login, password, Role.CLIENT.getRole());
        }
        userService.update(id, userMapper.toEntity(userRequest));
        UserResponse userResponse = userMapper.toResponse(userMapper.toEntity(userRequest));
        resp.getWriter().println(userResponse);
    }
}
