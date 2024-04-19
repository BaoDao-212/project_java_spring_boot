package com.amigoscode.orderItem;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amigoscode.apiResponse.ApiResponse;
import com.amigoscode.orderItem.request.OrderItemRequest;
import com.amigoscode.product.request.ProductRequest;

import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("api/order/items")
@RequiredArgsConstructor
public class OrderController {
    private final OrderItemService orderItemService;

    //update order item
    @PutMapping("/{id}")
    public ApiResponse<String> updateOrderItem(@PathVariable Long id, @RequestBody OrderItemRequest orderItem) {
        return new ApiResponse<String>(1000, null, orderItemService.updateOrderItem( orderItem,id));
    }
    //delete order item
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteOrderItem(@PathVariable Long id) {
        return new ApiResponse<String>(1000, null, orderItemService.deleteOrderItem(id));
    }
    //get order item by id
    @GetMapping("/{id}")
    public ApiResponse<OrderItem> getOrderItemById(@PathVariable Long id) {
        return new ApiResponse<OrderItem>(1000, null, orderItemService.getOrderItemById(id));
    }
}
