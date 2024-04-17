package com.amigoscode.auth;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amigoscode.apiResponse.ApiResponse;
import com.amigoscode.auth.request.AuthenticationRequest;
import com.amigoscode.auth.request.RegisterRequest;
import com.amigoscode.auth.response.AuthenticationResponse;
import com.amigoscode.user.User;
import com.amigoscode.user.request.ChangePasswordRequest;

import lombok.RequiredArgsConstructor;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> registerUser(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request) {
        return new ApiResponse(1000,null, authenticationService.login(request));
    }
}

