package org.aston.jdbc.controller.order;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.jdbc.domain.Order;
import org.aston.jdbc.dto.OrderResponse;
import org.aston.jdbc.mapper.OrderMapper;
import org.aston.jdbc.service.OrderServiceImpl;
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
public class OrderUpdateServletTest {
    @Mock
    private OrderServiceImpl orderService;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private PrintWriter printWriter;

    @InjectMocks
    private OrderUpdateServlet orderUpdateServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoPost() throws IOException, ServletException {
        Order order = mock(Order.class);
        OrderResponse orderResponse = mock(OrderResponse.class);

        when(httpServletRequest.getParameter(anyString())).thenReturn("");
        httpServletRequest.getParameter("key");
        httpServletRequest.getParameter("cost");
        httpServletRequest.getParameter("count");
        httpServletRequest.getParameter("date");
        httpServletRequest.getParameter("userId");
        httpServletRequest.getParameter("status");
        verify(httpServletRequest, times(6)).getParameter(anyString());
        doNothing().when(orderService).update(1L, order);
        orderService.update(1L, order);
        verify(orderService, times(1)).update(1L, order);
        lenient().when(orderMapper.toResponse(order)).thenReturn(orderResponse);
        lenient().when(httpServletResponse.getWriter()).thenReturn(printWriter);
        printWriter.println(orderResponse);
        verify(printWriter, times(1)).println(orderResponse);
    }
}
