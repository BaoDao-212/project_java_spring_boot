package com.amigoscode.order;


import java.security.Principal;
import java.sql.SQLException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.amigoscode.order.request.OrderRequest;
import com.amigoscode.product.ProductDao;
import com.amigoscode.user.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderDao repository;
    private final ProductDao productRepository;
   
    // methods change password
    public String createOrder( Principal connectedUser, OrderRequest[] request) throws SQLException {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        for (OrderRequest orderRequest : request) {
            
            var productId= orderRequest.getProductId();
            var product = productRepository.findProductById(productId);
            if(product==null){
                throw new IllegalStateException("Product not found");
            }
        }
        var data= repository.createOrder(request, user.getId());
        return "Created order successfully!";
    }
    // method update profile
    

}
