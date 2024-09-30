package org.aston.jdbc.mapper;

import org.aston.jdbc.domain.Product;
import org.aston.jdbc.dto.ProductRequest;
import org.aston.jdbc.dto.ProductResponse;
import org.aston.jdbc.enums.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductResponse toResponse(Product product);
    Product toEntity(ProductRequest productRequest);
    default Product mapResultSetToProduct(ResultSet resultSet) {
        Product product = new Product();
        try{
            product.setId(resultSet.getLong("id"));
            product.setName(resultSet.getString("name_product"));
            product.setCode(resultSet.getLong("code"));
            product.setPrice(resultSet.getBigDecimal("price"));
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}
