package org.aston.jdbc.mapper;

import org.aston.jdbc.domain.User;
import org.aston.jdbc.dto.UserRequest;
import org.aston.jdbc.dto.UserResponse;
import org.aston.jdbc.enums.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserResponse toResponse(User user);
    User toEntity(UserRequest userRequest);

    default User mapResultSetToUser(ResultSet resultSet) {
        User client = new User();
        try{
            client.setId(resultSet.getLong("id"));
            client.setName(resultSet.getString("first_name"));
            client.setSurname(resultSet.getString("last_name"));
            client.setLogin(resultSet.getString("login"));
            client.setPassword(resultSet.getString("password"));
            String roles = resultSet.getString("role");
            if(roles.equals("Admin")) {
                client.setRole(Role.ADMIN);
            }
            else {
                client.setRole(Role.CLIENT);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return client;
    }
}
