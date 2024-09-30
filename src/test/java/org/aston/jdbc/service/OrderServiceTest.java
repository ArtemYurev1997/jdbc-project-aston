package org.aston.jdbc.service;

import org.aston.jdbc.domain.Order;
import org.aston.jdbc.exception.NotFoundOrderException;
import org.aston.jdbc.repository.impl.OrderRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {
    @Mock
    private OrderRepositoryImpl orderRepository;

    @Mock
    private Order order;

    @Mock
    private List<Order> orders;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    public void addOrder_test() {
        doNothing().when(orderRepository).addOrder(order);
        orderRepository.addOrder(order);
        verify(orderRepository, times(1)).addOrder(order);
    }

    @Test
    public void getAllOrders_test() {
        when(orderRepository.getAllOrders()).thenReturn(orders);
        orderRepository.getAllOrders();
        verify(orderRepository, times(1)).getAllOrders();
    }

    @Test
    public void deleteOrder_test() {
        doNothing().when(orderRepository).delete(anyLong());
        orderRepository.delete(order.getId());
        verify(orderRepository, times(1)).delete(anyLong());
    }

    @Test
    public void findByIdOrder_test() {
        when(orderRepository.getOrderById(anyLong())).thenReturn(order);
        Order getOrder = orderService.getOrderById(anyLong());
        verify(orderRepository, times(1)).getOrderById(anyLong());
        assertEquals(order, getOrder);
    }

    @Test
    public void notFoundById_test() {
        Order order1 = new Order();
        lenient().when(orderRepository.getOrderById(anyLong())).thenReturn(order);
        NotFoundOrderException notFoundOrderException = assertThrows(NotFoundOrderException.class, () -> orderService.getOrderById(order1.getId()));
        assertEquals(notFoundOrderException.getMessage(), "Not found order with id " + order1.getId());
    }


    @Test
    public void updateOrder_test() {
        doNothing().when(orderRepository).update(anyLong(), eq(order));
        orderRepository.update(1L, order);
        verify(orderRepository, times(1)).update(anyLong(), eq(order));
    }

}
