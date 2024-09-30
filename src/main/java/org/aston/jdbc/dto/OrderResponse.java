package org.aston.jdbc.dto;

import org.aston.jdbc.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

public class OrderResponse {
    private Long id;
    private String key;
    private BigDecimal cost;
    private Integer count;
    private LocalDateTime localDateTime;
    private Status status;
    private Long userResponseId;
    private Set<ProductResponse> productResponses;

    public OrderResponse() {
    }

    public OrderResponse(Long id, String key, BigDecimal cost, Integer count, LocalDateTime localDateTime, Status status) {
        this.id = id;
        this.key = key;
        this.cost = cost;
        this.count = count;
        this.localDateTime = localDateTime;
        this.status = status;
    }

    public OrderResponse(Long id, String key, BigDecimal cost, Integer count, LocalDateTime localDateTime, Status status, Long userResponseId, Set<ProductResponse> productResponses) {
        this.id = id;
        this.key = key;
        this.cost = cost;
        this.count = count;
        this.localDateTime = localDateTime;
        this.status = status;
        this.userResponseId = userResponseId;
        this.productResponses = productResponses;
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

    public Long getUserResponseId() {
        return userResponseId;
    }

    public void setUserResponseId(Long userResponseId) {
        this.userResponseId = userResponseId;
    }

    public Set<ProductResponse> getProductResponses() {
        return productResponses;
    }

    public void setProductResponses(Set<ProductResponse> productResponses) {
        this.productResponses = productResponses;
    }

    @Override
    public String toString() {
        return "OrderResponse{" +
                "id=" + id +
                ", key='" + key + '\'' +
                ", cost=" + cost +
                ", count=" + count +
                ", localDateTime=" + localDateTime +
                ", status=" + status +
                ", userResponse=" + userResponseId +
                ", productResponses=" + productResponses +
                '}';
    }
}
