package com.amigoscode.order;


import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRowMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Order(
               resultSet.getLong("order_id"),
               resultSet.getString("order_name"),
               resultSet.getDate("order_date").toLocalDate()
        );
    }
}
