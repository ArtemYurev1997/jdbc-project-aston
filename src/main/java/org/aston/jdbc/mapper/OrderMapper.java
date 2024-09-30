package org.aston.jdbc.mapper;

import org.aston.jdbc.domain.Order;
import org.aston.jdbc.dto.OrderRequest;
import org.aston.jdbc.dto.OrderResponse;
import org.aston.jdbc.enums.Role;
import org.aston.jdbc.enums.Status;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderResponse toResponse(Order order);
    Order toEntity(OrderRequest orderRequest);
    default Order mapResultSetToOrder(ResultSet resultSet) {
        Order order = new Order();
        try{
            order.setId(resultSet.getLong("id"));
            order.setKey(resultSet.getString("key_order"));
            order.setCost(resultSet.getBigDecimal("cost_order"));
            order.setCount(resultSet.getInt("count_order"));
            order.setDate(resultSet.getTimestamp("date_order"));
            String status = resultSet.getString("status");
            order.setUserId(resultSet.getLong("user_id"));
            if(status.equals("Unformed")) {
                order.setStatus(Status.UNFORMED);
            }
            else if(status.equals("On the way")){
                order.setStatus(Status.ON_THE_WAY);
            }
            else if(status.equals("Waiting")){
                order.setStatus(Status.WAITING_FOR_THE_COURIER);
            }
            else {
                order.setStatus(Status.DONE);
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return order;
    }
}
