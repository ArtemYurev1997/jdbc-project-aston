package org.aston.jdbc.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.aston.jdbc.api.OrderApi;
import org.aston.jdbc.contract.OrderService;
import org.aston.jdbc.dto.OrderRequest;
import org.aston.jdbc.dto.OrderResponse;
import org.aston.jdbc.mapper.OrderMapper;


import java.util.List;

@Path("/orders")
public class OrderController implements OrderApi {

    @Inject
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public OrderResponse addProduct(OrderRequest orderRequest) {
        return orderMapper.toResponse(orderService.addOrder(orderMapper.toEntity(orderRequest)));
    }

    @POST
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Override
    public Response delete(Long id) {
        orderService.deleteOrder(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<OrderResponse> getAllProducts() {
        return orderService.getAllOrders().stream().map(orderMapper::toResponse).toList();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public OrderResponse findOrderById(@PathParam(value = "id") Long id) {
        return orderMapper.toResponse(orderService.getOrderById(id));
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public List<OrderResponse> findOrdersByUserId(@PathParam(value = "userId") Long id) {
        return orderService.getOrdersByUserId(id).stream().map(orderMapper::toResponse).toList();
    }

    @PUT
    @Path("/update/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Override
    public Response update(@PathParam(value = "id")Long id, OrderRequest orderRequest) {
        orderService.update(id, orderMapper.toEntity(orderRequest));
        return Response.ok().build();
    }

}
