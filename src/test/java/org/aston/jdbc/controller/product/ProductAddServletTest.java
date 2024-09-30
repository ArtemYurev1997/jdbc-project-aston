package org.aston.jdbc.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.jdbc.domain.Order;
import org.aston.jdbc.domain.Product;
import org.aston.jdbc.dto.OrderRequest;
import org.aston.jdbc.dto.OrderResponse;
import org.aston.jdbc.dto.ProductRequest;
import org.aston.jdbc.dto.ProductResponse;
import org.aston.jdbc.mapper.ProductMapper;
import org.aston.jdbc.service.ProductServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class ProductAddServletTest {
    @Mock
    private ProductServiceImpl productService;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private HttpServletRequest httpServletRequest;

    @Mock
    private HttpServletResponse httpServletResponse;

    @Mock
    private PrintWriter printWriter;

    @InjectMocks
    private ProductAddServlet productAddServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoPost() throws IOException, ServletException {
        ProductRequest productRequest = mock(ProductRequest.class);
        Product product = mock(Product.class);
        ProductResponse productResponse = mock(ProductResponse.class);
        List<Product> products = mock(List.class);

        when(httpServletRequest.getParameter(anyString())).thenReturn(anyString());
        httpServletRequest.getParameter("name");
        httpServletRequest.getParameter("code");
        httpServletRequest.getParameter("price");
        verify(httpServletRequest, times(3)).getParameter(anyString());
        lenient().when(productService.addProduct(productMapper.toEntity(productRequest))).thenReturn(product);

        lenient().when(productService.getAllProducts()).thenReturn(products);
        lenient().when(products.get(anyInt())).thenAnswer(invocation -> invocation
                .<List<Product>>getArgument(0).stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList()));
        lenient().when(httpServletResponse.getWriter()).thenReturn(printWriter);
        printWriter.println(productResponse);
        verify(printWriter, times(1)).println(productResponse);
//        orderAddServlet.doPost(httpServletRequest, httpServletResponse);
    }


}
