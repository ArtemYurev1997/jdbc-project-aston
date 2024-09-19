package org.aston.jdbc.repository;

import org.aston.jdbc.config.JdbcConnection;
import org.aston.jdbc.domain.Order;
import org.aston.jdbc.domain.User;
import org.aston.jdbc.enums.Role;
import org.aston.jdbc.mapper.OrderMapper;
import org.aston.jdbc.mapper.UserMapper;
import org.aston.jdbc.repository.impl.OrderRepositoryImpl;
import org.aston.jdbc.repository.impl.UserRepositoryImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.sql.*;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;


@Testcontainers
public class UserRepositoryTest {

    public static DockerImageName container = DockerImageName.parse("postgres:16-alpine");

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(container)
            .withDatabaseName("testdb")
            .withUsername("postgres")
            .withPassword("sa")
            .withExposedPorts(5432)
            .withInitScript("sql/data.sql");


    public UserRepository userRepository;
    public UserMapper userMapper;
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
        userMapper = UserMapper.INSTANCE;
        userRepository = new UserRepositoryImpl(jdbcConnection, userMapper);
        orderMapper = OrderMapper.INSTANCE;
        orderRepository = new OrderRepositoryImpl(jdbcConnection, orderMapper);
    }

    @Test
    void shouldGetUserAndOrderByUserId_test() {
        User user = userRepository.getUserById(1L);
        List<Order> orders = orderRepository.getOrdersByUserId(1L);
        user.setOrders(orders);
        assertEquals(1L, user.getId());
        assertEquals("Anton", user.getName());
        System.out.println(user);
    }

    @Test
    void addUser_test() {
        userRepository.addUser(new User( "Andrey", "Andreev", "Andro646", "Andro777", Role.CLIENT));
        List<User> users = userRepository.getAllUsers();
        assertEquals(2, users.size());
        System.out.println(users);
    }

    @Test
    void deleteUser_test() {
        userRepository.addUser(new User( "Andrey", "Andreev", "Andro646", "Andro777", Role.CLIENT));
        userRepository.addUser(new User( "Aurele", "Anurele", "Aura122", "Aura1997", Role.CLIENT));
        userRepository.delete(2L);
        List<User> users = userRepository.getAllUsers();
        assertEquals(2, users.size());
        System.out.println(users);
    }

    @Test
    void updateUser_test() {
        userRepository.addUser(new User( "Aurele", "Anurele", "Aura122", "Aura1997", Role.CLIENT));
        userRepository.update(1L, new User("Antuan", "Antuanov", "Antuan122", "Antuan1201", Role.CLIENT));
        List<User> users = userRepository.getAllUsers();
        assertEquals(2, users.size());
        System.out.println(users);
    }

}
