package com.amigoscode.order;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amigoscode.apiResponse.ApiResponse;
import com.amigoscode.order.request.OrderRequest;
import com.amigoscode.user.request.UpdateUserRequest;

import lombok.RequiredArgsConstructor;

import java.security.Principal;
import java.sql.SQLException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    @PostMapping("/create")
    public ApiResponse<String> createOrder(Principal user, @RequestBody OrderRequest request) throws SQLException {
        return new ApiResponse(1000,null, orderService.createOrder(user,request));
    }
}

