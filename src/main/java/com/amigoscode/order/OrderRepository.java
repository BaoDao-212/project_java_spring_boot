package com.amigoscode.order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.amigoscode.order.request.OrderRequest;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class OrderRepository implements OrderDao {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public int createOrder(OrderRequest orderRequest, Long userId) {
        int result = 0;
        try (Connection connection = jdbcTemplate.getDataSource().getConnection()) {
            // Tắt chế độ tự động commit
            connection.setAutoCommit(false);
    
            // Thao tác 1: INSERT INTO orders
            jdbcTemplate.update("INSERT INTO orders(user_id, order_date) VALUES (?, ?)",
                    userId, new Date()
            );
    
            // Lấy id của order mới được tạo ở trên
            Long orderId = jdbcTemplate.queryForObject("SELECT lastval()", Long.class);
    
            // Thao tác 2: INSERT INTO order_items
            jdbcTemplate.update("INSERT INTO orderItems(order_id, product_id, quantity, price) VALUES (?, ?, ?, ?)",
                    orderId, orderRequest.getProductId(), orderRequest.getNumberProduct(), 12.00);
    
            // Thao tác 3: UPDATE products
            jdbcTemplate.update("UPDATE products SET numberproducts = numberproducts - ? WHERE product_id = ?",
                    orderRequest.getNumberProduct(), orderRequest.getProductId());
            System.out.println("Order created successfully!");
    
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
    public Order findOrderById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findOrderById'");
    }

}
