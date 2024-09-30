package org.aston.jdbc.api;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.aston.jdbc.dto.ProductRequest;
import org.aston.jdbc.dto.ProductResponse;

import java.util.List;

public interface ProductApi {
    ProductResponse addProduct(ProductRequest productRequest);

    Response delete(Long id);

    List<ProductResponse> getAllProducts();

    ProductResponse findProductById(Long id);

    Response update(Long id, ProductRequest productRequest);
}
