package org.aston.jdbc.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.jdbc.domain.Order;
import org.aston.jdbc.domain.Product;
import org.aston.jdbc.dto.OrderResponse;
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

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductGetByIdServletTest {
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
    private ProductGetByIdServlet productGetByIdServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoPost() throws IOException, ServletException {
        Product product = mock(Product.class);
        ProductResponse productResponse = mock(ProductResponse.class);

        when(httpServletRequest.getParameter(anyString())).thenReturn("");
        when(httpServletRequest.getParameter(anyString())).thenReturn("1");
        lenient().when(productService.getProductById(anyLong())).thenReturn(product);
        productService.getProductById(anyLong());
        verify(productService, times(1)).getProductById(anyLong());
        lenient().when(productMapper.toResponse(product)).thenReturn(productResponse);
        when(httpServletResponse.getWriter()).thenReturn(printWriter);
        printWriter.println(productResponse);
        productGetByIdServlet.doPost(httpServletRequest, httpServletResponse);
        verify(printWriter, times(1)).println(productResponse);
    }
}
