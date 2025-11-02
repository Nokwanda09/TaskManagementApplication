package com.singabenkosimpungose.taskmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;

import com.singabenkosimpungose.taskmanagement.models.User;
import com.singabenkosimpungose.taskmanagement.services.UserService;
import com.singabenkosimpungose.taskmanagement.DTOs.UserDTO;

import java.util.Map;

@RestController
// @CrossOrigin(origins = "http://127.0.0.1:5500")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    public String getUsername(Authentication authentication){
        return authentication.getName();
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody UserDTO loginUser){
        String username = loginUser.getUsername().toLowerCase();
        loginUser.setUsername(username);
        String userExists =  userService.verifyUser(loginUser);
        String fullName = userService.findUserByUsername(username).getFullName();
        System.out.println(userExists);
        return ResponseEntity.ok(Map.of("token",userExists, "fullName", fullName));
    }


    @SuppressWarnings("unchecked")
    @PostMapping("/register")
    public <T> ResponseEntity<T> registerUser(@RequestBody User user){
        try{
            user.setUsername(user.getUsername().toLowerCase());
            User newUser = userService.createUser(user);
            return (ResponseEntity<T>) ResponseEntity.ok(newUser);
        } catch (DataIntegrityViolationException error){
            return (ResponseEntity<T>) ResponseEntity.badRequest().body("User with username already exist.");
        }
    }


    @GetMapping("/fullName")
    public ResponseEntity<Map<String, String>> getFullName(Authentication authorization){
        return ResponseEntity.ok(Map.of("fullName",userService.findUserByUsername(getUsername(authorization)).getFullName()));
    }
    
}
