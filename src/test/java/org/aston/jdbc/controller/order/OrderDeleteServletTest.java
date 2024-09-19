package org.aston.jdbc.controller.order;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.jdbc.domain.Order;
import org.aston.jdbc.dto.OrderRequest;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderDeleteServletTest {
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
    private OrderDeleteByIdServlet orderDeleteByIdServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoPost() throws IOException, ServletException {
        List<Order> orderList = new ArrayList<>();
        List<Order> orders = mock(List.class);
        List<OrderResponse> orderResponses = new ArrayList<>();

        when(httpServletRequest.getParameter(anyString())).thenReturn("");
        when(httpServletRequest.getParameter(anyString())).thenReturn("1");
        doNothing().when(orderService).deleteOrder(anyLong());
        orderService.deleteOrder(anyLong());
        verify(orderService, times(1)).deleteOrder(anyLong());

        lenient().when(orderService.getAllOrders()).thenReturn(orderList);
        lenient().when(orders.get(anyInt())).thenAnswer(invocation -> invocation
                .<List<Order>>getArgument(0).stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList()));
        when(httpServletResponse.getWriter()).thenReturn(printWriter);
        orderDeleteByIdServlet.doPost(httpServletRequest, httpServletResponse);
        verify(printWriter, times(1)).println(orderResponses);
    }
}


