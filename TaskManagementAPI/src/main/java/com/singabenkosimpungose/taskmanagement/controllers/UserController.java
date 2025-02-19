package com.singabenkosimpungose.taskmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.singabenkosimpungose.taskmanagement.models.User;
import com.singabenkosimpungose.taskmanagement.services.UserService;
import com.singabenkosimpungose.taskmanagement.DTOs.UserLoginDTO;

import java.util.Map;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://127.0.0.1:5050")
public class UserController {

    @Autowired
    private UserService UserService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Boolean>> loginUser(@RequestBody UserLoginDTO loginRequest){
        boolean userExists =  UserService.userExists(loginRequest.getEmail());
        return new ResponseEntity<>(Map.of("exists", userExists), HttpStatus.OK);
    }

    @PostMapping("/create")
    public User registerUser(@RequestBody User user){
        return UserService.createUser(user);
    }

    
    @DeleteMapping("/delete")
    public void deleteUser(@RequestParam String name, @RequestParam String email){
        UserService.deleteUser(name, email);
    }
}
