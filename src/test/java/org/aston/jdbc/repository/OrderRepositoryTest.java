package org.aston.jdbc.repository;

import org.aston.jdbc.config.JdbcConnection;
import org.aston.jdbc.domain.Order;
import org.aston.jdbc.enums.Status;
import org.aston.jdbc.mapper.OrderMapper;
import org.aston.jdbc.repository.impl.OrderRepositoryImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class OrderRepositoryTest {
    public static DockerImageName container = DockerImageName.parse("postgres:16-alpine");

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(container)
            .withDatabaseName("testdb")
            .withUsername("postgres")
            .withPassword("sa")
            .withExposedPorts(5432)
            .withInitScript("sql/data.sql");

    public OrderRepository orderRepository;
    public OrderMapper orderMapper;

    @BeforeAll
    static void startContainers() {
        postgres.start();
    }

    @AfterAll
    static void stopContainers() {
        postgres.stop();
    }

    @BeforeEach
    void setUp() {
        JdbcConnection jdbcConnection = () -> {
            try {
                return DriverManager.getConnection(postgres.getJdbcUrl(),
                        postgres.getUsername(),
                        postgres.getPassword());
            } catch (Throwable e) {
                throw new RuntimeException(e.getMessage());
            }
        };
        orderMapper = OrderMapper.INSTANCE;
        orderRepository = new OrderRepositoryImpl(jdbcConnection, orderMapper);
    }

    @Test
    void addOrder_test() {
        orderRepository.addOrder(new Order("fanta-key", new BigDecimal("2.5"), 1, new Timestamp(System.currentTimeMillis()), Status.UNFORMED, 1L));
        Order order = orderRepository.getOrderById(2L);
        assertEquals(2L, order.getId());
        assertEquals("fanta-key", order.getKey());
        System.out.println(order);
    }

    @Test
    void getOrderById_test() {
        Order order = orderRepository.getOrderById(1L);
        assertEquals(1L, order.getId());
        assertEquals("cola-key", order.getKey());
        System.out.println(order);
    }

    @Test
    void deleteOrder_test() {
        orderRepository.addOrder(new Order("fanta-key", new BigDecimal("2.5"), 1, new Timestamp(System.currentTimeMillis()), Status.UNFORMED, 1L));
        orderRepository.delete(1L);
        List<Order> orders = orderRepository.getAllOrders();
        System.out.println(orders);
        Order order = orderRepository.getOrderById(2L);
        assertEquals(2L, order.getId());
        assertEquals("fanta-key", order.getKey());
        System.out.println(order);
    }

    @Test
    void updateOrder_test() {
        orderRepository.addOrder(new Order("fanta-key", new BigDecimal("2.5"), 1, new Timestamp(System.currentTimeMillis()), Status.UNFORMED, 1L));
        orderRepository.update(1L, new Order("sprite-key", new BigDecimal("2.6"), 1, new Timestamp(System.currentTimeMillis()), Status.UNFORMED, 1L));
        Order order = orderRepository.getOrderById(1L);
        assertEquals(1L, order.getId());
        assertEquals("sprite-key", order.getKey());
        List<Order> orders = orderRepository.getAllOrders();
        System.out.println(orders);
    }
}
