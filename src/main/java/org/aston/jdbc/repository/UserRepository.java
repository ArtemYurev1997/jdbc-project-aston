package org.aston.jdbc.repository;

import org.aston.jdbc.domain.User;

import java.util.List;

public interface UserRepository {
    void addUser(User user);

    List<User> getAllUsers();

    User getUserById(Long id);

    User getUser();

    void delete(Long id);

    User checkPassword(String login, String password);

    void update(Long id, User user);


}
