package org.aston.jdbc.controller.order;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.jdbc.controller.user.UserAddServlet;
import org.aston.jdbc.domain.Order;
import org.aston.jdbc.domain.User;
import org.aston.jdbc.dto.OrderRequest;
import org.aston.jdbc.dto.OrderResponse;
import org.aston.jdbc.dto.UserRequest;
import org.aston.jdbc.dto.UserResponse;
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
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServletAddTest {
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
    private OrderAddServlet orderAddServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoPost() throws IOException, ServletException {
        OrderRequest orderRequest = mock(OrderRequest.class);
        Order order = mock(Order.class);
        OrderResponse orderResponse = mock(OrderResponse.class);
        List<Order> orders = mock(List.class);

        when(httpServletRequest.getParameter(anyString())).thenReturn(anyString());
        httpServletRequest.getParameter("key");
        httpServletRequest.getParameter("cost");
        httpServletRequest.getParameter("count");
        httpServletRequest.getParameter("date");
        httpServletRequest.getParameter("userId");
        httpServletRequest.getParameter("status");
        verify(httpServletRequest, times(6)).getParameter(anyString());
        lenient().when(orderService.addOrder(orderMapper.toEntity(orderRequest))).thenReturn(order);

        lenient().when(orderService.getAllOrders()).thenReturn(orders);
        lenient().when(orders.get(anyInt())).thenAnswer(invocation -> invocation
                .<List<Order>>getArgument(0).stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList()));
        lenient().when(httpServletResponse.getWriter()).thenReturn(printWriter);
        printWriter.println(orderResponse);
        verify(printWriter, times(1)).println(orderResponse);
//        orderAddServlet.doPost(httpServletRequest, httpServletResponse);
    }
}
