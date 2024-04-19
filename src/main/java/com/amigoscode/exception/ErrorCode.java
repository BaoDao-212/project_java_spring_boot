package com.amigoscode.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum  ErrorCode {
    WRONG_PASSWORD(1001, "The password is wrong!", HttpStatus.UNAUTHORIZED),
    INVALID_KEY (1002, "The key is invalid!", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND (1003, "The user is not found!", HttpStatus.NOT_FOUND),
    UNAUTHORIED(1004, "The user is unauthorized!", HttpStatus.UNAUTHORIZED),
    INTERNAL_SERVER_ERROR (1005, "An internal server error occurred!", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_ARGUMENT (1006, "The argument is invalid!", HttpStatus.BAD_REQUEST),
    UNCATEGORIZED_EXCEPTION (9999, "An uncategorized exception occurred!", HttpStatus.INTERNAL_SERVER_ERROR),
    // order item
    ORDER_ITEM_NOT_FOUND(2001, "The order item is not found!", HttpStatus.NOT_FOUND),
    // order
    ORDER_NOT_FOUND(2002, "The order is not found!", HttpStatus.NOT_FOUND),
    // product
    PRODUCT_NOT_FOUND(3001, "The product is not found!", HttpStatus.NOT_FOUND),
    PRODUCT_QUANTITY_NOT_ENOUGH (3002, "The product quantity is not enough!", HttpStatus.BAD_REQUEST),
    // category
    CATEGORY_NOT_FOUND(4001, "The category is not found!", HttpStatus.NOT_FOUND),
    // user
    USER_ALREADY_EXISTS(5001, "The user already exists!", HttpStatus.BAD_REQUEST);
    // Getters and setters omitted for brevity

    private int code;
    private String message;
    private HttpStatusCode status;

    // Constructor omitted for brevity
    ErrorCode(int code, String message, HttpStatusCode status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
    public HttpStatusCode getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
