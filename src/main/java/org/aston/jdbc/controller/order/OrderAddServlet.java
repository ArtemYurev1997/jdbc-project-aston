package org.aston.jdbc.controller.order;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.jdbc.contract.OrderService;
import org.aston.jdbc.dto.OrderRequest;
import org.aston.jdbc.dto.OrderResponse;
import org.aston.jdbc.dto.ProductRequest;
import org.aston.jdbc.dto.ProductResponse;
import org.aston.jdbc.enums.Status;
import org.aston.jdbc.mapper.OrderMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class OrderAddServlet extends HttpServlet {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderAddServlet(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var orders = orderService.getAllOrders();
        List<OrderResponse> orderResponseList = orders.stream().map(orderMapper::toResponse).toList();
        resp.getWriter().println(orderResponseList);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String key = req.getParameter("key");
        BigDecimal cost = BigDecimal.valueOf(Long.parseLong(req.getParameter("cost")));
        Integer count = Integer.valueOf(req.getParameter("count"));
        LocalDateTime date = LocalDateTime.parse(req.getParameter("date"));
        Long userId = Long.valueOf(req.getParameter("userId"));
        OrderRequest orderRequest;
        if (req.getParameter("status").equals("Unformed")) {
            orderRequest = new OrderRequest(key, cost, count, date, userId, Status.UNFORMED);
        }
        else if (req.getParameter("status").equals("On the way")) {
            orderRequest = new OrderRequest(key, cost, count, date, userId, Status.ON_THE_WAY);
        }
        else if (req.getParameter("status").equals("Waiting")) {
            orderRequest = new OrderRequest(key, cost, count, date, userId, Status.WAITING_FOR_THE_COURIER);
        }
        else {
            orderRequest = new OrderRequest(key, cost, count, date, userId, Status.DONE);
        }
        orderService.addOrder(orderMapper.toEntity(orderRequest));
        doGet(req, resp);
    }
}
