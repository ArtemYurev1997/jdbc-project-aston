package org.aston.jdbc.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.jdbc.contract.ProductService;
import org.aston.jdbc.dto.ProductResponse;
import org.aston.jdbc.mapper.ProductMapper;

import java.io.IOException;
import java.util.List;

public class ProductGetAllServlet extends HttpServlet {
    private final ProductService productService;
    private final ProductMapper productMapper;


    public ProductGetAllServlet(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var products = productService.getAllProducts();
        List<ProductResponse> productResponseList = products.stream().map(productMapper::toResponse).toList();
        resp.getWriter().println(productResponseList);
    }
}
