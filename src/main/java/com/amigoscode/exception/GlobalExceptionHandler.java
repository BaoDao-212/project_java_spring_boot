package com.amigoscode.exception;

import org.hibernate.LazyInitializationException;
import org.postgresql.util.PSQLException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amigoscode.apiResponse.ApiResponse;

import io.jsonwebtoken.MalformedJwtException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = PSQLException.class)
    ResponseEntity<ApiResponse> handlePSQLException(PSQLException e) {
        return ResponseEntity.status(401).body(new ApiResponse(401, e.getMessage(), null));
    }
    @ExceptionHandler(value = RuntimeException.class)
    ResponseEntity<ApiResponse> handleRuntimeException(RuntimeException e) {
        return ResponseEntity.status(401).body(new ApiResponse(401, e.getMessage(), null));
    }
    @ExceptionHandler(value = MalformedJwtException.class)
    ResponseEntity<ApiResponse> handleMalformedJwtException(MalformedJwtException e) {
        System.out.println(e.getMessage());
        return ResponseEntity.status(400).body(new ApiResponse(401, e.getMessage(), null));
    }
    @ExceptionHandler(value = Exception.class)
    ResponseEntity<ApiResponse> handleException(Exception e) {
        return ResponseEntity.status(400).body(new ApiResponse(401, e.getMessage(), null));
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<ApiResponse> handleAppException(AppException e) {
        ErrorCode errorCode = e.getErrorCode();
        return ResponseEntity.status(errorCode.getStatus())
                .body(new ApiResponse(errorCode.getCode(), errorCode.getMessage(), null));
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse> handleExceptionValidatEntity(MethodArgumentNotValidException e) {
        String enumKey = e.getFieldError().getDefaultMessage();
        ErrorCode errorCode = ErrorCode.INVALID_KEY;
        try {
            errorCode = ErrorCode.valueOf(enumKey);
        } catch (IllegalArgumentException ex) {
        }
        return ResponseEntity.status(errorCode.getStatus())
                .body(new ApiResponse(errorCode.getCode(), errorCode.getMessage(), null));
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    ResponseEntity<ApiResponse> handleAccessDeniedException(AccessDeniedException e) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIED;

        return ResponseEntity.status(errorCode.getStatus())
                .body(new ApiResponse(errorCode.getCode(), errorCode.getMessage(), null));
    }

    @ExceptionHandler(value = LazyInitializationException.class)
    ResponseEntity<ApiResponse> handleAccessDeniedException(LazyInitializationException e) {
        ErrorCode errorCode = ErrorCode.UNAUTHORIED;

        return ResponseEntity.status(errorCode.getStatus())
                .body(new ApiResponse(errorCode.getCode(), errorCode.getMessage(), null));
    }

}
