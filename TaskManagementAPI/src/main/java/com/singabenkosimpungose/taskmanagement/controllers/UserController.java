package com.singabenkosimpungose.taskmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.singabenkosimpungose.taskmanagement.models.User;
import com.singabenkosimpungose.taskmanagement.services.UserService;
import com.singabenkosimpungose.taskmanagement.DTOs.UserDTO;

import java.util.Map;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, Boolean>> loginUser(@RequestBody UserDTO loginUser){
        Boolean userExists =  userService.verifyUser(loginUser);
        return new ResponseEntity<>(Map.of("userExists", userExists), HttpStatus.OK);
    }


    @SuppressWarnings("unchecked")
    @PostMapping("/register")
    public <T> ResponseEntity<T> registerUser(@RequestBody User user){
        try{
            User newUser = userService.createUser(user);
            return (ResponseEntity<T>) ResponseEntity.ok(newUser);
        } catch (DataIntegrityViolationException error){
            return (ResponseEntity<T>) ResponseEntity.badRequest().body("User with username already exist.");
        }
    }

    
    // @DeleteMapping("/delete")
    // public void deleteUser(@RequestParam String name, @RequestParam String email){
    //     UserService.deleteUser(name, email);
    // }
}
