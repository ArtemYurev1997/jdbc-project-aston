package org.aston.jdbc.api;

import jakarta.ws.rs.core.Response;
import org.aston.jdbc.dto.UserRequest;
import org.aston.jdbc.dto.UserResponse;

import java.util.List;

public interface UserApi {
    Response register(UserRequest userRequest);

    UserResponse getUser();

    UserResponse authorise(String login, String password);

    Response delete(Long id);

    List<UserResponse> getAllClients();

    UserResponse findUserById(Long id);

    Response update(Long id, UserRequest userRequest);
}
