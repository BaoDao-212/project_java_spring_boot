package com.amigoscode.order;

import java.sql.SQLException;

import com.amigoscode.order.request.OrderRequest;

public interface OrderDao {
    int createOrder(OrderRequest[] orderRequest,Long userId) throws SQLException;
    Order findOrderById(Long id);
}
