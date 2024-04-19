package com.amigoscode.orderItem
;

import java.util.List;

import com.amigoscode.orderItem.request.OrderItemRequest;

public interface OrderItemDao {
    OrderItem findOrderItemById(Long id);
    OrderItem findOrderItemByName(String name);
    int createOrderItem(OrderItem orderItem);
    int updateOrderItem(OrderItem orderItem, Long id,Long changeQuanlity);
    int deleteOrderItem(Long id);
    List<OrderItem> findAllOrderItems(Long id);
}
