package com.amigoscode.auth;


import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amigoscode.auth.request.AuthenticationRequest;
import com.amigoscode.auth.request.RegisterRequest;
import com.amigoscode.auth.response.AuthenticationResponse;
import com.amigoscode.config.auth.JwtService;
import com.amigoscode.exception.AppException;
import com.amigoscode.exception.ErrorCode;
import com.amigoscode.user.Role;
import com.amigoscode.user.User;
import com.amigoscode.user.UserDao;
import com.amigoscode.user.request.ChangePasswordRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserDao repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder().username(registerRequest.getUsername()).email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword())).role( List.of(Role.USER))
                .build();
        try {
            var savedUser = repository.registerUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public  AuthenticationResponse login(AuthenticationRequest request) {
        System.out.println(request.getUsername() + " " + request.getPassword());
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
            )
        );
        System.out.println("Authenticated");
        User user =  repository.findUserByUsername(request.getUsername());
        System.out.println(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}
