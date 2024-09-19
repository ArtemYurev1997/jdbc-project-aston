package org.aston.jdbc.contract;

import org.aston.jdbc.domain.Order;


import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    void update(Long id, Order order);

    void deleteOrder(Long id);

    Order addOrder(Order order);

    Order getOrderById(Long id);

    List<Order> getOrdersByUserId(Long userId);
}
