package org.aston.jdbc.domain;

import org.aston.jdbc.enums.Role;

import java.util.List;
import java.util.Set;

public class User {
    private Long id;

    private String name;

    private String surname;

    private String login;

    private String password;

    private Role role;

    private List<Order> orders;

    public User() {
    }

    public User(Long id, String name, String surname, String login, String password, Role role, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role = role;
        this.orders = orders;
    }

    public User(Long id, String name, String surname, String login, String password, Role role) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public User(String name, String surname, String login, String password, Role role, List<Order> orders) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role = role;
        this.orders = orders;
    }

    public User(String name, String surname, String login, String password, Role role) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public void addOrders(Order order) {
        this.orders.add(order);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role=" + role +
                ", orders=" + orders +
                '}';
    }
}
