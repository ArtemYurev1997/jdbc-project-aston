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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserUpdateServletTest {
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
    private UserUpdateServlet userUpdateServlet;

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
        doNothing().when(userService).update(1L, user);
        userService.update(1L, user);
        verify(userService, times(1)).update(1L, user);
        lenient().when(userMapper.toResponse(user)).thenReturn(userResponse);
        when(httpServletResponse.getWriter()).thenReturn(printWriter);
        printWriter.println(userResponse);
        verify(printWriter, times(1)).println(userResponse);
        userUpdateServlet.doPost(httpServletRequest, httpServletResponse);
    }
}
