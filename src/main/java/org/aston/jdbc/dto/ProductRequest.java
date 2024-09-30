package org.aston.jdbc.dto;

import org.aston.jdbc.domain.Order;

import java.util.Set;

public class ProductRequest {
    private Long id;
    private String name;
    private Long code;
    private Double price;

    public ProductRequest() {
    }

    public ProductRequest(Long id, String name, Long code, Double price) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public ProductRequest(String name, Long code, Double price) {
        this.name = name;
        this.code = code;
        this.price = price;
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

    @Override
    public String toString() {
        return "ProductRequest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code=" + code +
                ", price=" + price +
                '}';
    }
}
