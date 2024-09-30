package org.aston.jdbc.contract;

import org.aston.jdbc.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    void update(Long id, Product product);

    void deleteProduct(Long id);

    Product addProduct(Product product);

    Product getProductById(Long id);
}
