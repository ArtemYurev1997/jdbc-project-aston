package org.aston.jdbc.service;

import org.aston.jdbc.domain.User;
import org.aston.jdbc.exception.NotFoundUserException;
import org.aston.jdbc.repository.impl.UserRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepositoryImpl userRepository;

    @Mock
    private User user;

    @Mock
    private List<User> users;

    @InjectMocks
    private UserServiceImpl userService;


    @Test
    public void addUser_test() {
        doNothing().when(userRepository).addUser(user);
        userRepository.addUser(user);
        verify(userRepository, times(1)).addUser(user);
    }

    @Test
    public void getAllUsers_test() {
        when(userRepository.getAllUsers()).thenReturn(users);
        userRepository.getAllUsers();
        verify(userRepository, times(1)).getAllUsers();
    }

    @Test
    public void delete_test() {
        doNothing().when(userRepository).delete(anyLong());
        userRepository.delete(user.getId());
        verify(userRepository, times(1)).delete(anyLong());
    }

    @Test
    public void findById_test() {
        when(userRepository.getUserById(anyLong())).thenReturn(user);
        User getUser = userService.findById(anyLong());
        verify(userRepository, times(1)).getUserById(anyLong());
        assertEquals(user, getUser);
    }

    @Test
    public void notFoundById_test() {
        User user1 = new User();
        lenient().when(userRepository.getUserById(anyLong())).thenReturn(user);
        NotFoundUserException notFoundUserException = assertThrows(NotFoundUserException.class, () -> userService.findById(user1.getId()));
        assertEquals(notFoundUserException.getMessage(), "Not found user with id " + user1.getId());
    }


    @Test
    public void update_test() {
        doNothing().when(userRepository).update(anyLong(), eq(user));
        userRepository.update(1L, user);
        verify(userRepository, times(1)).update(anyLong(), eq(user));
    }
}
