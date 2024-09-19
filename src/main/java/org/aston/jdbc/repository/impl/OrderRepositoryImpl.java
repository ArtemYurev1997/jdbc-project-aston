package org.aston.jdbc.repository.impl;

import org.aston.jdbc.config.JdbcConnection;
import org.aston.jdbc.domain.Order;
import org.aston.jdbc.domain.User;
import org.aston.jdbc.enums.Status;
import org.aston.jdbc.mapper.OrderMapper;
import org.aston.jdbc.repository.OrderRepository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderRepositoryImpl implements OrderRepository {
    private final String ADD_ORDER = "insert into order_table (id, key_order, cost_order, count_order, date_order, status, user_id) " +
            "values (?,?,?,?,?,?,?)";
    private final String MAX_ID = "select max(id) from order_table";
    private final String FIND_BY_PRODUCT_ID = "select * from order_table o join basket_table ba on o.id = ba.order_id where ba.product_id = ?";


    private final JdbcConnection jdbcConnection;
    private final OrderMapper orderMapper;

    public OrderRepositoryImpl(JdbcConnection jdbcConnection, OrderMapper orderMapper) {
        this.jdbcConnection = jdbcConnection;
        this.orderMapper = orderMapper;
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try(Connection connection = jdbcConnection.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeQuery("select * from order_table");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String key = resultSet.getString(2);
                BigDecimal cost = resultSet.getBigDecimal(3);
                Integer count = resultSet.getInt(4);
                Timestamp date = resultSet.getTimestamp(5);
                String status = resultSet.getString(6);
                Long userId = resultSet.getLong(7);
                Order order;
                if(status.equals("Unformed")) {
                    order = new Order(id, key, cost, count, date, Status.UNFORMED, userId);
                }
                else if(status.equals("Waiting")) {
                    order = new Order(id, key, cost, count, date, Status.WAITING_FOR_THE_COURIER, userId);
                }
                else if(status.equals("On the way")) {
                    order = new Order(id, key, cost, count, date, Status.ON_THE_WAY, userId);
                }
                else {
                    order = new Order(id, key, cost, count, date, Status.DONE, userId);
                }
                orders.add(order);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return orders;
    }

    public Order getOrder() {
        Order order = null;
        try(Connection connection = jdbcConnection.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeQuery(MAX_ID);
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                order = getOrderById(Long.valueOf(id));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return order;
    }


    @Override
    public Order getOrderById(Long id) {
        try(Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from order_table o where o.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return new Order();
            }
            resultSet.next();
            return orderMapper.mapResultSetToOrder(resultSet);
        }
        catch(SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try(Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from order_table where id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void addOrder(Order order) {
        try(Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatementMaxId = connection.prepareStatement(MAX_ID);
            ResultSet resultSet = preparedStatementMaxId.executeQuery();
            resultSet.next();
            var maxId = resultSet.getLong(1);

            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ORDER);
            preparedStatement.setLong(1, ++maxId);
            preparedStatement.setString(2, order.getKey());
            preparedStatement.setBigDecimal(3, order.getCost());
            preparedStatement.setDouble(4, order.getCount());
            preparedStatement.setTimestamp(5, order.getDate());
            preparedStatement.setString(6, order.getStatus().toString());
            preparedStatement.setLong(7, order.getUserId());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Long id, Order order) {
        try(Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE order_table SET key_order=?, cost_order=?, count_order=?, date_order=?, status=?, user_id=?  WHERE id=?");
            preparedStatement.setLong(7, id);
            preparedStatement.setString(1, order.getKey());
            preparedStatement.setBigDecimal(2, order.getCost());
            preparedStatement.setDouble(3, order.getCount());
            preparedStatement.setTimestamp(4, order.getDate());
            preparedStatement.setString(5, order.getStatus().toString());
            preparedStatement.setLong(6, order.getUserId());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        List<Order> orders = new ArrayList<>();
        try(Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from order_table o where o.user_id=?");
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                long id = resultSet.getLong(1);
                String key = resultSet.getString(2);
                BigDecimal cost = resultSet.getBigDecimal(3);
                Integer count = resultSet.getInt(4);
                Timestamp date = resultSet.getTimestamp(5);
                String status = resultSet.getString(6);
                Order order;
                if (status.equals("Unformed")) {
                    order = new Order(id, key, cost, count, date, Status.UNFORMED, userId);
                } else if (status.equals("Waiting")) {
                    order = new Order(id, key, cost, count, date, Status.WAITING_FOR_THE_COURIER, userId);
                } else if (status.equals("On the way")) {
                    order = new Order(id, key, cost, count, date, Status.ON_THE_WAY, userId);
                } else {
                    order = new Order(id, key, cost, count, date, Status.DONE, userId);
                }
                orders.add(order);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return orders;
    }

    public Set<Order> getOrdersByProductId(Long productId) {
        Set<Order> orderSet = new HashSet<>();
        try(Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_PRODUCT_ID);
            preparedStatement.setLong(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return new HashSet<>();
            }
            Order order = null;
            while (resultSet.next()) {
                order = orderMapper.mapResultSetToOrder(resultSet);
            }
            orderSet.add(order);
        }
        catch(SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
        return orderSet;
    }

    @Override
    public Order getOrderByUserId(Long userId) {
        try(Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from order_table o where o.user_id=?");
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return new Order();
            }
            resultSet.next();
            return orderMapper.mapResultSetToOrder(resultSet);
        }
        catch(SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
