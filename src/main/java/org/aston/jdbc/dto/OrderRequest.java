package org.aston.jdbc.dto;

import org.aston.jdbc.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderRequest {
    private Long id;
    private String key;
    private BigDecimal cost;
    private Integer count;
    private LocalDateTime localDateTime;
    private Long userId;
    private Status status;

    public OrderRequest() {
    }

    public OrderRequest(Long id, String key, BigDecimal cost, Integer count, LocalDateTime localDateTime, Long userId, Status status) {
        this.id = id;
        this.key = key;
        this.cost = cost;
        this.count = count;
        this.localDateTime = localDateTime;
        this.userId = userId;
        this.status = status;
    }

    public OrderRequest(String key, BigDecimal cost, Integer count, LocalDateTime localDateTime, Long userId, Status status) {
        this.key = key;
        this.cost = cost;
        this.count = count;
        this.localDateTime = localDateTime;
        this.userId = userId;
        this.status = status;
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

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", cost=" + cost +
                ", count=" + count +
                ", localDateTime=" + localDateTime +
                ", status=" + status +
                '}';
    }
}
