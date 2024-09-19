package org.aston.jdbc.repository.impl;

import org.aston.jdbc.config.JdbcConnection;
import org.aston.jdbc.domain.User;
import org.aston.jdbc.enums.Role;
import org.aston.jdbc.mapper.UserMapper;
import org.aston.jdbc.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {
    private final String ADD_USER = "insert into user_table (id, first_name, last_name, login, password, role) " +
            "values (?,?,?,?,?,?)";
    private final String MAX_ID = "select max(id) from user_table";
    private final String NEXT_ID = "select * from user_table where id=(select max(id) from user_table)";


    private final JdbcConnection jdbcConnection;
    private final UserMapper userMapper;

    public UserRepositoryImpl(JdbcConnection jdbcConnection, UserMapper userMapper) {
        this.jdbcConnection = jdbcConnection;
        this.userMapper = userMapper;
    }

    @Override
    public void addUser(User user) {
        try(Connection connection = jdbcConnection.getConnection()) {;
            PreparedStatement preparedStatementMaxId = connection.prepareStatement(MAX_ID);
            ResultSet resultSet = preparedStatementMaxId.executeQuery();
            resultSet.next();
            var id = resultSet.getLong(1);

            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER);
            preparedStatement.setLong(1, ++id);
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getLogin());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setString(6, String.valueOf(user.getRole()));
            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try(Connection connection = jdbcConnection.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeQuery("select * from user_table");
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                String login = resultSet.getString(4);
                String password = resultSet.getString(5);
                String role = resultSet.getString(6);
                User user;
                if(role.equals("Admin")) {
                    user = new User(Long.valueOf(id), name, surname, login, password, Role.ADMIN);
                }
                else {
                    user = new User(Long.valueOf(id), name, surname, login, password, Role.CLIENT);
                }
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    public User getUser() {
        User user = null;
        try(Connection connection = jdbcConnection.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeQuery(NEXT_ID);
            ResultSet resultSet = statement.getResultSet();
            if (resultSet == null) {
                return new User();
            }
            while(resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String surname = resultSet.getString(3);
                String login = resultSet.getString(4);
                String password = resultSet.getString(5);
                String role = resultSet.getString(6);
                if (role.equals("Admin")) {
                    user = new User(Long.valueOf(id), name, surname, login, password, Role.ADMIN);
                } else {
                    user = new User(Long.valueOf(id), name, surname, login, password, Role.CLIENT);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void delete(Long id) {
        try(Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from user_table where id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User getUserById(Long id) {
        try(Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from user_table u where u.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return new User();
            }
            resultSet.next();
            return userMapper.mapResultSetToUser(resultSet);
        }
        catch(SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public User checkPassword(String login, String password) {
        try(Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from testsch.user u where u.login_user=? AND u.password_user=?");
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return new User();
            }
            resultSet.next();
            return userMapper.mapResultSetToUser(resultSet);
        }
        catch(SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public void update(Long id, User user) {
        try(Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user_table SET first_name=?, last_name=?, login=?, password=?, role=? WHERE id=?");
            preparedStatement.setLong(6, id);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, String.valueOf(user.getRole()));
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
