package org.aston.jdbc.controller.user;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.jdbc.contract.UserService;
import org.aston.jdbc.domain.User;
import org.aston.jdbc.dto.UserRequest;
import org.aston.jdbc.dto.UserResponse;
import org.aston.jdbc.enums.Role;
import org.aston.jdbc.mapper.UserMapper;

import java.io.IOException;

public class UserAddServlet extends HttpServlet {
    private final UserService userService;
    private final UserMapper userMapper;

    @Inject
    public UserAddServlet(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("first_name");
        String surname = req.getParameter("last_name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (req.getParameter("role").equals("Admin")) {
            UserRequest userRequest = new UserRequest(name, surname, login, password, Role.ADMIN.toString());
            User user = userService.addUser(userMapper.toEntity(userRequest));
            UserResponse userResponse = userMapper.toResponse(user);
            resp.setContentType("application/json");
            resp.getWriter().println(userResponse);
        } else {
            UserRequest userRequest = new UserRequest(name, surname, login, password, Role.CLIENT.toString());
            userService.addUser(userMapper.toEntity(userRequest));
            User user = userService.addUser(userMapper.toEntity(userRequest));
            UserResponse userResponse = userMapper.toResponse(user);
            resp.setContentType("application/json");
            resp.getWriter().println(userResponse);
        }
    }
}
