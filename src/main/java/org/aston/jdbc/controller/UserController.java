package org.aston.jdbc.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.aston.jdbc.api.UserApi;
import org.aston.jdbc.contract.UserService;
import org.aston.jdbc.dto.UserRequest;
import org.aston.jdbc.dto.UserResponse;
import org.aston.jdbc.mapper.UserMapper;

import java.util.List;

//@Path("/users")
public class UserController  {

    private final UserService userService;
    private final UserMapper userMapper;

    @Inject
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

//    @POST
//    @Path("/add")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Override
//    public Response register(UserRequest userRequest) {
////        UserResponse user = authorise(userRequest.getLogin(), userRequest.getPassword());
////        if(user == null) {
//            UserResponse userResponse = userMapper.toResponse(userService.addUser(userMapper.toEntity(userRequest)));
//            return Response.ok().entity(userResponse).build();
////        }
////        return Response.ok().entity(user).build();
//    }
//
//
//    public UserResponse getUser() {
//        return userMapper.toResponse(userService.getClient());
//    }
//
//    @Override
//    public UserResponse authorise(String login, String password) {
//        return userMapper.toResponse(userService.authorise(login, password));
//    }
//
//    @POST
//    @Path("/delete")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Override
//    public Response delete(Long id) {
//        userService.delete(id);
//        return Response.noContent().build();
//    }
//
//    @GET
//    @Path("/getAll")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Override
//    public List<UserResponse> getAllClients() {
//        return userService.getAllUsers().stream().map(userMapper::toResponse).toList();
//    }
//
//    @GET
//    @Path("/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Override
//    public UserResponse findUserById(@PathParam(value = "id") Long id) {
//        return userMapper.toResponse(userService.findById(id));
//    }
//
//    @PUT
//    @Path("/update/{id}")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Override
//    public Response update(@PathParam(value = "id")Long id, UserRequest userRequest) {
//        userService.update(id, userMapper.toEntity(userRequest));
//        return Response.ok().build();
//    }
}
