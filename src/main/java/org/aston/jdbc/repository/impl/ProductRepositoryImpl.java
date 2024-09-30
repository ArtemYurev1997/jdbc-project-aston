package org.aston.jdbc.repository.impl;

import org.aston.jdbc.config.JdbcConnection;
import org.aston.jdbc.domain.Product;
import org.aston.jdbc.mapper.ProductMapper;
import org.aston.jdbc.repository.ProductRepository;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {
    private final String ADD_PRODUCT = "insert into product_table (id, name_product, code, price) " +
            "values (?,?,?,?)";
    private final String MAX_ID = "select max(id) from product_table";

    private final JdbcConnection jdbcConnection;
    private final ProductMapper productMapper;

    public ProductRepositoryImpl(JdbcConnection jdbcConnection, ProductMapper productMapper) {
        this.jdbcConnection = jdbcConnection;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        try(Connection connection = jdbcConnection.getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeQuery("select * from product_table");
            ResultSet resultSet = statement.getResultSet();
            while(resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String code = resultSet.getString(3);
                String price = resultSet.getString(4);
                Product product = new Product(Long.valueOf(id), name, Long.valueOf(code), BigDecimal.valueOf(Double.parseDouble(price)));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public void update(Long id, Product product) {
        try (Connection connection = jdbcConnection.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE product_table SET name_product=?, code=?, price=? WHERE id=?");
            preparedStatement.setLong(4, id);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setLong(2, product.getCode());
            preparedStatement.setBigDecimal(3, product.getPrice());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public void deleteProduct(Long id) {
        try(Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("delete from product_table where id=?");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void addProduct(Product product) {
        try(Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatementMaxId = connection.prepareStatement(MAX_ID);
            ResultSet resultSet = preparedStatementMaxId.executeQuery();
            resultSet.next();
            var maxId = resultSet.getLong(1);

            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT);
            preparedStatement.setLong(1, ++maxId);
            preparedStatement.setString(2, product.getName());
            preparedStatement.setLong(3, product.getCode());
            preparedStatement.setBigDecimal(4, product.getPrice());
            preparedStatement.execute();
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Product getProductById(Long id) {
        try (Connection connection = jdbcConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("select * from product_table u where u.id=?");
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet == null) {
                return new Product();
            }
            resultSet.next();
            return productMapper.mapResultSetToProduct(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
