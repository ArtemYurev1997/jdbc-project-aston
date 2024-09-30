package org.aston.jdbc.service;

import org.aston.jdbc.contract.OrderService;
import org.aston.jdbc.domain.Order;
import org.aston.jdbc.exception.NotFoundOrderException;
import org.aston.jdbc.repository.OrderRepository;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.getAllOrders();
    }

    @Override
    public void update(Long id, Order order) {
        orderRepository.update(id, order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.delete(id);
    }

    @Override
    public Order addOrder(Order order) {
        orderRepository.addOrder(order);
        return order;
    }

    @Override
    public Order getOrderById(Long id) {
        Order order = orderRepository.getOrderById(id);
        if(order == null) {
            throw new NotFoundOrderException("Not found order with id " + id);
        }
        return order;
    }

    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.getOrdersByUserId(userId);
    }
}
