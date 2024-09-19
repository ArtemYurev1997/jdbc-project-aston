package org.aston.jdbc.controller.product;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.jdbc.contract.ProductService;
import org.aston.jdbc.dto.ProductResponse;
import org.aston.jdbc.mapper.ProductMapper;

import java.io.IOException;

public class ProductGetByIdServlet extends HttpServlet {
    private final ProductService productService;
    private final ProductMapper productMapper;


    public ProductGetByIdServlet(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        var product = productService.getProductById(id);
        ProductResponse productResponse = productMapper.toResponse(product);
        resp.getWriter().println(productResponse);
    }
}
