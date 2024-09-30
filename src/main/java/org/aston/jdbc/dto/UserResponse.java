package org.aston.jdbc.dto;

import org.aston.jdbc.enums.Role;

import java.util.Set;

public class UserResponse {
    private Long id;
    private String name;
    private String surname;
    private String login;
    private Role role;
    private Set<OrderResponse> orderResponses;

    public UserResponse() {
    }

    public UserResponse(String name, String surname, String login, Role role) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.role = role;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Set<OrderResponse> getOrderResponses() {
        return orderResponses;
    }

    public void setOrderResponses(Set<OrderResponse> orderResponses) {
        this.orderResponses = orderResponses;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", role=" + role +
                ", orderResponses=" + orderResponses +
                '}';
    }
}
