package com.amigoscode.orderItem;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OrderItemRowMapper  implements RowMapper<OrderItem>{
    @Override
    public OrderItem mapRow(ResultSet resultSet, int i) throws SQLException {
        return new OrderItem(
                resultSet.getLong("order_item_id"),
                resultSet.getLong("order_id"),
                resultSet.getLong("product_id"),
               resultSet.getBigDecimal("price").doubleValue(),
                resultSet.getLong("quantity")
        );
    }
}
