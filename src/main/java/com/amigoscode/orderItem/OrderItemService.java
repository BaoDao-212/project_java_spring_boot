package com.amigoscode.orderItem;

import java.security.Principal;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.amigoscode.exception.AppException;
import com.amigoscode.exception.ErrorCode;
import com.amigoscode.order.OrderDao;
import com.amigoscode.orderItem.request.OrderItemCreateRequest;
import com.amigoscode.orderItem.request.OrderItemRequest;
import com.amigoscode.product.ProductDao;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderItemService {
    OrderItemDao repository;
    ProductDao productRepository;
    OrderDao orderRepository;

    // methods change order item
    public String updateOrderItem(OrderItemRequest request, Long id) {
        var getOrderItem = repository.findOrderItemById(id);
            
        if (getOrderItem == null) {
            throw new AppException(ErrorCode.ORDER_ITEM_NOT_FOUND);
        }
        var product = productRepository.findProductById(getOrderItem.getProduct_id());
        if (product == null) {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        if(product.getNumberProducts() < request.getQuanlity() - getOrderItem.getQuanlity()){
            throw new AppException(ErrorCode.PRODUCT_QUANTITY_NOT_ENOUGH);
        }
        Long changeQuanlity = request.getQuanlity() - getOrderItem.getQuanlity(); 
        getOrderItem.setPrice(request.getPrice());
        getOrderItem.setQuanlity(request.getQuanlity().longValue());
        repository.updateOrderItem(getOrderItem, id,changeQuanlity);
        return "Updated order successfully";
    }

    // method delete order item
    public String deleteOrderItem(Long id) {
        var getOrderItem = repository.findOrderItemById(id);
        if (getOrderItem == null) {
            throw new AppException(ErrorCode.ORDER_ITEM_NOT_FOUND);
        }
        repository.deleteOrderItem(id);
        return "Deleted order successfully";
    }

    public String createOrderItem(OrderItemCreateRequest orderItemCreateRequest) {
        var orderItem = OrderItem.builder()
                .order_id(orderItemCreateRequest.getOrderId().longValue()) // Change the argument type from Integer to
                .product_id(orderItemCreateRequest.getProductId().longValue())
                .price(orderItemCreateRequest.getPrice())
                .quanlity(orderItemCreateRequest.getQuanlity().longValue())
                .build();
        var product = productRepository.findProductById(orderItemCreateRequest.getProductId().longValue());
        if (product == null) {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }
        var order = orderRepository.findOrderById(orderItemCreateRequest.getOrderId().longValue());
        if (order == null) {
            throw new AppException(ErrorCode.ORDER_NOT_FOUND);
        }
        if (orderItemCreateRequest.getQuanlity() > product.getNumberProducts()) {
            throw new AppException(ErrorCode.PRODUCT_QUANTITY_NOT_ENOUGH);
        }
        var data = repository.createOrderItem(orderItem);
        return "Created order item successfully!";
    }

    // method get all order item of order
    public List<OrderItem> getAllOrderItem(Long orderId) {
        return repository.findAllOrderItems(orderId);
    }

    // method get order item by id
    public OrderItem getOrderItemById(Long id) {
        return repository.findOrderItemById(id);
    }
}
