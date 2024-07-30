package com.jbdl.user_service.controller;

import com.jbdl.user_service.request.UserRequest;
import com.jbdl.user_service.response.UserResponse;
import com.jbdl.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/create/user")
    public ResponseEntity<UserResponse> createuser(@RequestBody UserRequest userRequest){
        UserResponse userResponse = userService.saveUser(userRequest);
        if(!userResponse.getMessage().contains("created")){
            return new ResponseEntity<UserResponse>(userResponse, HttpStatus.OK);
        }
        return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
    }

    @GetMapping("/show/user")
    public String publicUrl(){
        return "public url";
    }

    @GetMapping("/user/credentials")
    public String fetchUserCredentials(@RequestParam("mobileNo") String mobileNo) {
        String credentials = userService.getUserDetails(mobileNo);
        return credentials;
    }
}
