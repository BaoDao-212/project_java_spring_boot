package com.amigoscode.orderItem;

import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.amigoscode.orderItem.request.OrderItemRequest;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderItemRepository implements OrderItemDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public OrderItem findOrderItemById(Long id) {
        var sql = "SELECT * FROM orderItems WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new OrderItemRowMapper(), id);
    }

    @Override
    public OrderItem findOrderItemByName(String name) {
        var sql = "SELECT * FROM orderItems WHERE name = ?";
        return jdbcTemplate.queryForObject(sql, new OrderItemRowMapper(), name);
    }

    @Override
    public int createOrderItem(OrderItem OrderItem) {
                int result = 0;
                try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
                    // Tắt chế độ tự động commit
                    connection.setAutoCommit(false);
                    // Thao tác 1: 
                    var sql1 = "INSERT INTO orderItems (order_id,product_id,price,quanlity) VALUES (?, ?, ?, ?)";
                     jdbcTemplate.update(sql1, OrderItem.getOrder_id(), OrderItem.getProduct_id(), OrderItem.getPrice());
                    
                    // Thao tác 2:
                        jdbcTemplate.update("UPDATE products SET quantity = quantity - ? WHERE id = ?",
                               OrderItem.getQuanlity(), OrderItem.getProduct_id());
                 
                    System.out.println("Order item was created and product quantity was updated successfully!");
                    // Xác nhận giao dịch
                    connection.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        jdbcTemplate.getDataSource().getConnection().rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                } finally {
                    try {
                        jdbcTemplate.getDataSource().getConnection().setAutoCommit(true);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                return result;
    }

    @Override
    public int updateOrderItem(OrderItem OrderItem, Long id,Long quanlityChange) {
        int result = 0;
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            // Tắt chế độ tự động commit
            connection.setAutoCommit(false);
            // Thao tác 1: 
            jdbcTemplate.update("UPDATE orderItems SET quantity = ?, price = ?",
                    OrderItem.getQuanlity(), OrderItem.getPrice(), id);
            
            // Thao tác 2:
                jdbcTemplate.update("UPDATE products SET quantity = quantity - ? WHERE id = ?",
                       quanlityChange, OrderItem.getProduct_id());
         
            System.out.println("Order item was updated and product quantity was updated successfully!");
            // Xác nhận giao dịch
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                jdbcTemplate.getDataSource().getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } finally {
            try {
                jdbcTemplate.getDataSource().getConnection().setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public int deleteOrderItem(Long id) {
        var sql = "DELETE FROM orderItems WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<OrderItem> findAllOrderItems(Long id) {
        var sql = "SELECT * FROM orderItems WHERE order_id = ?";
        return jdbcTemplate.query(sql, new OrderItemRowMapper(), id);
    }

}
