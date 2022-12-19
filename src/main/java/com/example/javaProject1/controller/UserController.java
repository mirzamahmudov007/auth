package com.example.javaProject1.controller;

import com.example.javaProject1.entity.User;
import com.example.javaProject1.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController( UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (!userService.existUsername(user.getUsername())) {
            return new ResponseEntity<>("this username is busy", HttpStatus.BAD_REQUEST);
        }
        if (!checkPassword(user.getPassword())) {
            return new ResponseEntity<>("password length is less than 4 characters", HttpStatus.BAD_REQUEST);
        }
        User result = userService.save(user);
        return ResponseEntity.ok("successfully");
    }

    @GetMapping("/user/test")
    public String hello(){
        return "Hello user:)";
    }

    protected boolean checkPassword(String password) {
        return password.length() > 4;
    }


}

