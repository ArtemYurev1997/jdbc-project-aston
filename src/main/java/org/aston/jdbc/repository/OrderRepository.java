package org.aston.jdbc.repository;

import org.aston.jdbc.domain.Order;

import java.util.List;
import java.util.Set;

public interface OrderRepository {

    Set<Order> getOrdersByProductId(Long productId);

    List<Order> getAllOrders();

    Order getOrder();

    Order getOrderById(Long id);

    void delete(Long id);

    void addOrder(Order order);

    void update(Long id, Order order);

    List<Order> getOrdersByUserId(Long userId);

    Order getOrderByUserId(Long userId);

}
