package org.aston.jdbc.dto;

import java.util.Set;

public class ProductResponse {
    private Long id;
    private String name;
    private Long code;
    private Double price;
    private Set<OrderResponse> orderResponses;

    public ProductResponse() {
    }

    public ProductResponse(Long id, String name, Long code, Double price, Set<OrderResponse> orderResponses) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.price = price;
        this.orderResponses = orderResponses;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Set<OrderResponse> getOrderResponses() {
        return orderResponses;
    }

    public void setOrderResponses(Set<OrderResponse> orderResponses) {
        this.orderResponses = orderResponses;
    }

    @Override
    public String toString() {
        return "ProductResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code=" + code +
                ", price=" + price +
                ", orderResponses=" + orderResponses +
                '}';
    }
}
