package org.aston.jdbc.controller.product;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aston.jdbc.contract.ProductService;
import org.aston.jdbc.dto.ProductRequest;
import org.aston.jdbc.mapper.ProductMapper;

import java.io.IOException;

public class ProductUpdateServlet extends HttpServlet {
    private final ProductService productService;
    private final ProductMapper productMapper;


    public ProductUpdateServlet(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        String name = req.getParameter("name");
        Long code = Long.valueOf(req.getParameter("code"));
        Double price = Double.valueOf(req.getParameter("price"));
        ProductRequest productRequest  = new ProductRequest(name, code, price);
        productService.update(id, productMapper.toEntity(productRequest));
    }
}
