package com.amigoscode.orderItem.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemCreateRequest {
    private Integer productId;
    private Integer orderId;
    private Integer quanlity;
    private Double price;
}
