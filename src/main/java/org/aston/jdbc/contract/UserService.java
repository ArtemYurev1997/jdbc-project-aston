package org.aston.jdbc.contract;

import org.aston.jdbc.domain.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    User getClient();

    List<User> getAllUsers();

    void delete(Long id);

    User findById(Long id);

    User authorise(String login, String password);

    void update(Long id, User user);
}
