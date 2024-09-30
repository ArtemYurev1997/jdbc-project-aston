package org.aston.jdbc.controller.user;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.jdbc.domain.User;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserDeleteServletTest {
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
    private UserDeleteByIdServlet userDeleteByIdServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoPost() throws IOException, ServletException {
        List<User> usersMockList = mock(List.class);
        List<User> users = new ArrayList<>();
        List<UserResponse> userResponseList =new ArrayList<>();

        when(httpServletRequest.getParameter(anyString())).thenReturn("");
        when(httpServletRequest.getParameter(anyString())).thenReturn("1");
        doNothing().when(userService).delete(anyLong());
        userService.delete(anyLong());
        verify(userService, times(1)).delete(anyLong());

        lenient().when(userService.getAllUsers()).thenReturn(users);
        lenient().when(usersMockList.get(anyInt())).thenAnswer(invocation -> invocation
                .<List<User>>getArgument(0).stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList()));
        when(httpServletResponse.getWriter()).thenReturn(printWriter);
        userDeleteByIdServlet.doPost(httpServletRequest, httpServletResponse);
        verify(printWriter, times(1)).println(userResponseList);
    }
}
