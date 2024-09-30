package org.aston.jdbc.repository;

import org.aston.jdbc.domain.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> getAllProducts();

    void update(Long id, Product product);

    void deleteProduct(Long id);

    void addProduct(Product product);

    Product getProductById(Long id);




}
