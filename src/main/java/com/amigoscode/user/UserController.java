package com.amigoscode.user;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amigoscode.apiResponse.ApiResponse;
import com.amigoscode.user.request.ChangePasswordRequest;
import com.amigoscode.user.request.UpdateUserRequest;

import lombok.RequiredArgsConstructor;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
   
    @PutMapping("/changePassword")
    public ApiResponse<String> putMethodName( Principal user, @RequestBody ChangePasswordRequest request) {
        return new ApiResponse(1000,null, userService.changePassword(user,request));
    }
    @PutMapping("/update")
    public ApiResponse<String> putMethodName(Principal user, @RequestBody UpdateUserRequest request) {
        return new ApiResponse(1000,null, userService.updateProfile(user,request));
    }
}

