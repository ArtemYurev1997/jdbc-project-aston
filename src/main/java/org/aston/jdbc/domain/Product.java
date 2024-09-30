package org.aston.jdbc.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


public class Product {
    private Long id;

    private String name;

    private Long code;

    private BigDecimal price;

    private Set<Order> orders = new HashSet<>();

    public Product() {
    }

    public Product(String name, Long code, BigDecimal price) {
        this.name = name;
        this.code = code;
        this.price = price;
    }

    public Product(Long id, String name, Long code, BigDecimal price) {
        this.id = id;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code=" + code +
                ", price=" + price +
                ", orders=" + orders +
                '}';
    }
}
