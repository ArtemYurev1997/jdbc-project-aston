package org.aston.jdbc.controller.order;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.jdbc.contract.OrderService;
import org.aston.jdbc.dto.OrderResponse;
import org.aston.jdbc.mapper.OrderMapper;

import java.io.IOException;

public class OrderGetByIdServlet extends HttpServlet {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderGetByIdServlet(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        var order = orderService.getOrderById(id);
        OrderResponse orderResponse = orderMapper.toResponse(order);
        resp.getWriter().println(orderResponse);
    }
}
