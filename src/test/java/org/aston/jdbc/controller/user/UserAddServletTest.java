package org.aston.jdbc.controller.user;


import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.jdbc.domain.User;
import org.aston.jdbc.dto.UserRequest;
import org.aston.jdbc.dto.UserResponse;
import org.aston.jdbc.mapper.UserMapper;
import org.aston.jdbc.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.PrintWriter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class UserAddServletTest {
    @Mock
    private UserServiceImpl userService;

    @Mock
    private UserMapper userMapper;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private PrintWriter printWriter;

    @InjectMocks
    private UserAddServlet userAddServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoPost() throws IOException {
        UserRequest userRequest = mock(UserRequest.class);
        User user = mock(User.class);
        UserResponse userResponse = mock(UserResponse.class);

        when(httpServletRequest.getParameter(anyString())).thenReturn(anyString());
        httpServletRequest.getParameter("first_name");
        httpServletRequest.getParameter("last_name");
        httpServletRequest.getParameter("login");
        httpServletRequest.getParameter("password");
        httpServletRequest.getParameter("role");
        verify(httpServletRequest, times(5)).getParameter(anyString());
        lenient().when(userService.addUser(userMapper.toEntity(userRequest))).thenReturn(user);
        lenient().when(userMapper.toResponse(user)).thenReturn(userResponse);
        httpServletResponse.setContentType("application/json");
        when(httpServletResponse.getWriter()).thenReturn(printWriter);
        printWriter.println(userResponse);
        userAddServlet.doPost(httpServletRequest, httpServletResponse);
        verify(printWriter, times(1)).println(userResponse);
    }
}
