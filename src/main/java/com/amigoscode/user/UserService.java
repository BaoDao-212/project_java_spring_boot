package com.amigoscode.user;


import java.security.Principal;
import java.sql.SQLException;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.amigoscode.auth.request.RegisterRequest;
import com.amigoscode.auth.response.AuthenticationResponse;
import com.amigoscode.config.auth.JwtService;
import com.amigoscode.exception.AppException;
import com.amigoscode.exception.ErrorCode;
import com.amigoscode.user.Role;
import com.amigoscode.user.User;
import com.amigoscode.user.UserDao;
import com.amigoscode.user.request.ChangePasswordRequest;
import com.amigoscode.user.request.UpdateUserRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

   
    // methods change password
    public String changePassword( Principal connectedUser, ChangePasswordRequest request) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
        String oldPassword = request.getOldPassword();
        String newPassword = request.getNewPassword();
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new AppException(ErrorCode.WRONG_PASSWORD);
        }
        if (request.getNewPassword().equals(request.getOldPassword())) {
            throw new IllegalStateException("New password is same the old password");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        repository.updatePasswordUser(user);
        return "Password updated successfully!";
    }
    // method update profile
    public String updateProfile( Principal connectedUser, UpdateUserRequest request) {
        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
       
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());;
        repository.updateUser(user);
        return "Updated profile successfully!";
    }

}
