package org.aston.jdbc.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.jdbc.domain.User;
import org.aston.jdbc.dto.UserResponse;
import org.aston.jdbc.enums.Role;
import org.aston.jdbc.mapper.UserMapper;
import org.aston.jdbc.repository.impl.UserRepositoryImpl;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserGetByIdServletTest {
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
    private UserGetByIdServlet userGetByIdServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoPost() throws IOException, ServletException {
        User user = mock(User.class);
        UserResponse userResponse = mock(UserResponse.class);

        when(httpServletRequest.getParameter(anyString())).thenReturn("");
        when(httpServletRequest.getParameter(anyString())).thenReturn("1");
        lenient().when(userService.findById(anyLong())).thenReturn(user);
        userService.findById(anyLong());
        verify(userService, times(1)).findById(anyLong());
        lenient().when(userMapper.toResponse(user)).thenReturn(userResponse);
        when(httpServletResponse.getWriter()).thenReturn(printWriter);
        printWriter.println(userResponse);
        userGetByIdServlet.doPost(httpServletRequest, httpServletResponse);
        verify(printWriter, times(1)).println(userResponse);
    }
}
