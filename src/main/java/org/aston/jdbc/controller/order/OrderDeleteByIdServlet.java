package org.aston.jdbc.controller.order;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.jdbc.contract.OrderService;
import org.aston.jdbc.dto.OrderResponse;
import org.aston.jdbc.dto.ProductResponse;
import org.aston.jdbc.mapper.OrderMapper;

import java.io.IOException;
import java.util.List;

public class OrderDeleteByIdServlet extends HttpServlet {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderDeleteByIdServlet(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var products = orderService.getAllOrders();
        List<OrderResponse> orderResponseList = products.stream().map(orderMapper::toResponse).toList();
        resp.getWriter().println(orderResponseList);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        orderService.deleteOrder(id);
        doGet(req, resp);
    }
}
