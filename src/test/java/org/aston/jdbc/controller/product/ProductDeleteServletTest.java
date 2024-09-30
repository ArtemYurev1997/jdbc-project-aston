package org.aston.jdbc.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.jdbc.domain.Product;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductDeleteServletTest {
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
    private ProductDeleteByIdServlet productDeleteByIdServlet;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoPost() throws IOException, ServletException {
        List<Product> productList = new ArrayList<>();
        List<Product> products = mock(List.class);
        List<ProductResponse> productResponses = new ArrayList<>();

        when(httpServletRequest.getParameter(anyString())).thenReturn("");
        when(httpServletRequest.getParameter(anyString())).thenReturn("1");
        doNothing().when(productService).deleteProduct(anyLong());
        productService.deleteProduct(anyLong());
        verify(productService, times(1)).deleteProduct(anyLong());

        lenient().when(productService.getAllProducts()).thenReturn(productList);
        lenient().when(products.get(anyInt())).thenAnswer(invocation -> invocation
                .<List<Product>>getArgument(0).stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList()));
        when(httpServletResponse.getWriter()).thenReturn(printWriter);
        productDeleteByIdServlet.doPost(httpServletRequest, httpServletResponse);
        verify(printWriter, times(1)).println(productResponses);
    }
}
