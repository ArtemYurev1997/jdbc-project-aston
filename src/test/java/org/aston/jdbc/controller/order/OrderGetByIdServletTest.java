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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderGetByIdServletTest {
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
    private OrderGetByIdServlet orderGetByIdServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoPost() throws IOException, ServletException {
        Order order = mock(Order.class);
        OrderResponse orderResponse = mock(OrderResponse.class);

        when(httpServletRequest.getParameter(anyString())).thenReturn("");
        when(httpServletRequest.getParameter(anyString())).thenReturn("1");
        lenient().when(orderService.getOrderById(anyLong())).thenReturn(order);
        orderService.getOrderById(anyLong());
        verify(orderService, times(1)).getOrderById(anyLong());
        lenient().when(orderMapper.toResponse(order)).thenReturn(orderResponse);
        when(httpServletResponse.getWriter()).thenReturn(printWriter);
        printWriter.println(orderResponse);
        orderGetByIdServlet.doPost(httpServletRequest, httpServletResponse);
        verify(printWriter, times(1)).println(orderResponse);
    }
}
