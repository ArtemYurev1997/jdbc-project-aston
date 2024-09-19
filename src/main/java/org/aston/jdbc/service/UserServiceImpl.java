package org.aston.jdbc.service;

import org.aston.jdbc.contract.UserService;
import org.aston.jdbc.domain.User;
import org.aston.jdbc.exception.NotFoundUserException;
import org.aston.jdbc.repository.UserRepository;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        userRepository.addUser(user);
        return user;
    }

    public User getClient() {
        return userRepository.getUser();
    }

    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public void delete(Long id) {
        userRepository.delete(id);
    }

    public User findById(Long id) {
        User user = userRepository.getUserById(id);
        if(user == null) {
            throw new NotFoundUserException("Not found user with id " + id);
        }
        return user;
    }

    public User authorise(String login, String password) {
        return userRepository.checkPassword(login, password);
    }

    public void update(Long id, User user) {
        userRepository.update(id, user);
    }
}
