package org.aston.jdbc.api;

import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import org.aston.jdbc.dto.OrderRequest;
import org.aston.jdbc.dto.OrderResponse;

import java.util.List;

public interface OrderApi {
    OrderResponse addProduct(OrderRequest orderRequest);

    Response delete(Long id);

    List<OrderResponse> getAllProducts();

    OrderResponse findOrderById(Long id);

    List<OrderResponse> findOrdersByUserId(Long userId);

    Response update(Long id, OrderRequest orderRequest);
}
