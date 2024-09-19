package org.aston.jdbc.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.aston.jdbc.api.ProductApi;
import org.aston.jdbc.contract.ProductService;
import org.aston.jdbc.dto.ProductRequest;
import org.aston.jdbc.dto.ProductResponse;
import org.aston.jdbc.mapper.ProductMapper;

import java.util.List;

@Path("/products")
public class ProductController implements ProductApi {

    @Inject
    private final ProductService productService;
    private final ProductMapper productMapper;


    public ProductController(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        return productMapper.toResponse(productService.addProduct(productMapper.toEntity(productRequest)));
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response delete(Long id) {
        productService.deleteProduct(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts().stream().map(productMapper::toResponse).toList();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public ProductResponse findProductById(@PathParam(value = "id") Long id) {
        return productMapper.toResponse(productService.getProductById(id));
    }

    @PUT
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response update(@PathParam(value = "id")Long id, ProductRequest productRequest) {
        productService.update(id, productMapper.toEntity(productRequest));
        return Response.ok().build();
    }
}
