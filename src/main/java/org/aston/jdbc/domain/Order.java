package org.aston.jdbc.domain;

import org.aston.jdbc.domain.ref.ProductRef;
import org.aston.jdbc.enums.Status;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Order {

    private Long id;

    private String key;

    private BigDecimal cost;

    private Integer count;

    private Timestamp date;

    private Status status;

    private Long userId;

    private Set<ProductRef> products = new HashSet<>();

    public void addProduct(Product product) {
        this.products.add(new ProductRef(product.getId()));
    }

    public Set<Long> productIds() {
        return this.products.stream().map(ProductRef::getProduct).collect(Collectors.toSet());
    }


    public Order() {
    }

    public Order(Long id, String key, BigDecimal cost, Integer count, Timestamp date, Status status, Long userId) {
        this.id = id;
        this.key = key;
        this.cost = cost;
        this.count = count;
        this.date = date;
        this.status = status;
        this.userId = userId;
    }

    public Order(String key, BigDecimal cost, Integer count, Timestamp date, Status status, Long userId, Set<ProductRef> products) {
        this.key = key;
        this.cost = cost;
        this.count = count;
        this.date = date;
        this.status = status;
        this.userId = userId;
        this.products = products;
    }

    public Order(String key, BigDecimal cost, Integer count, Timestamp date, Status status, Long userId) {
        this.key = key;
        this.cost = cost;
        this.count = count;
        this.date = date;
        this.status = status;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<ProductRef> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductRef> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", cost=" + cost +
                ", count=" + count +
                ", date=" + date +
                ", status=" + status +
                ", userId=" + userId +
                ", products=" + products +
                '}';
    }
}
