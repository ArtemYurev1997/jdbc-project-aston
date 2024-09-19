package org.aston.jdbc.service;

import org.aston.jdbc.domain.Product;
import org.aston.jdbc.exception.NotFoundProductException;
import org.aston.jdbc.repository.impl.ProductRepositoryImpl;
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
public class ProductServiceTest {
    @Mock
    private ProductRepositoryImpl productRepository;

    @Mock
    private Product product;

    @Mock
    private List<Product> products;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    public void addProduct_test() {
        doNothing().when(productRepository).addProduct(product);
        productRepository.addProduct(product);
        verify(productRepository, times(1)).addProduct(product);
    }

    @Test
    public void getAllProducts_test() {
        when(productRepository.getAllProducts()).thenReturn(products);
        productRepository.getAllProducts();
        verify(productRepository, times(1)).getAllProducts();
    }

    @Test
    public void deleteProduct_test() {
        doNothing().when(productRepository).deleteProduct(anyLong());
        productRepository.deleteProduct(product.getId());
        verify(productRepository, times(1)).deleteProduct(anyLong());
    }

    @Test
    public void findByIdProduct_test() {
        when(productRepository.getProductById(anyLong())).thenReturn(product);
        Product getProduct = productService.getProductById(anyLong());
        verify(productRepository, times(1)).getProductById(anyLong());
        assertEquals(product, getProduct);
    }

    @Test
    public void notFoundById_test() {
        Product product1 = new Product();
        lenient().when(productRepository.getProductById(anyLong())).thenReturn(product);
        NotFoundProductException notFoundProductException = assertThrows(NotFoundProductException.class, () -> productService.getProductById(product1.getId()));
        assertEquals(notFoundProductException.getMessage(), "Not found product with id " + product1.getId());
    }


    @Test
    public void updateProduct_test() {
        doNothing().when(productRepository).update(anyLong(), eq(product));
        productRepository.update(1L, product);
        verify(productRepository, times(1)).update(anyLong(), eq(product));
    }
}
