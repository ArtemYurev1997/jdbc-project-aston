package org.aston.jdbc.service;

import org.aston.jdbc.contract.ProductService;
import org.aston.jdbc.domain.Product;
import org.aston.jdbc.exception.NotFoundProductException;
import org.aston.jdbc.repository.ProductRepository;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    @Override
    public void update(Long id, Product product) {
        productRepository.update(id, product);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteProduct(id);
    }

    @Override
    public Product addProduct(Product product) {
        productRepository.addProduct(product);
        return product;
    }

    @Override
    public Product getProductById(Long id) {
        Product product = productRepository.getProductById(id);
        if (product == null) {
            throw new NotFoundProductException("Not found product with id " + id);
        }
        return product;
    }
}
