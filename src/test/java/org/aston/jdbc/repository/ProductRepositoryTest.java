package org.aston.jdbc.repository;

import org.aston.jdbc.config.JdbcConnection;
import org.aston.jdbc.domain.Product;
import org.aston.jdbc.mapper.ProductMapper;
import org.aston.jdbc.repository.impl.ProductRepositoryImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;
import java.sql.DriverManager;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class ProductRepositoryTest {
    public static DockerImageName container = DockerImageName.parse("postgres:16-alpine");

    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(container)
            .withDatabaseName("testdb")
            .withUsername("postgres")
            .withPassword("sa")
            .withExposedPorts(5432)
            .withInitScript("sql/data.sql");

    public ProductRepository productRepository;
    public ProductMapper productMapper;

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
        productMapper = ProductMapper.INSTANCE;
        productRepository = new ProductRepositoryImpl(jdbcConnection, productMapper);
    }

    @Test
    void addProduct_test() {
        productRepository.addProduct(new Product("Fanta", 4234L, new BigDecimal("2.5")));
        Product product = productRepository.getProductById(2L);
        assertEquals(2L, product.getId());
        assertEquals("Fanta", product.getName());
        List<Product> products = productRepository.getAllProducts();
        assertEquals(2, products.size());
        System.out.println(products);
    }

    @Test
    void getProductById_test() {
        Product product = productRepository.getProductById(1L);
        assertEquals(1L, product.getId());
        assertEquals("Cola", product.getName());
        System.out.println(product);
    }

    @Test
    void deleteProduct_test() {
        productRepository.addProduct(new Product("Fanta", 4234L, new BigDecimal("2.5")));
        productRepository.deleteProduct(1L);
        List<Product> products = productRepository.getAllProducts();
        System.out.println(products);
        Product product = productRepository.getProductById(2L);
        assertEquals(2L, product.getId());
        assertEquals("Fanta", product.getName());
        System.out.println(product);
    }

    @Test
    void updateProduct_test() {
        productRepository.addProduct(new Product("Fanta", 4234L, new BigDecimal("2.5")));
        productRepository.update(1L, new Product("Bonaqua", 52626L, new BigDecimal("1.8")));
        Product product = productRepository.getProductById(2L);
        assertEquals(2L, product.getId());
        assertEquals("Fanta", product.getName());
        List<Product> products = productRepository.getAllProducts();
        System.out.println(products);
    }
}
