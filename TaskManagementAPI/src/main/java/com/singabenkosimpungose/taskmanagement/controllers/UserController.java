package com.singabenkosimpungose.taskmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.singabenkosimpungose.taskmanagement.models.User;
import com.singabenkosimpungose.taskmanagement.services.UserService;


@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService UserService;

    @GetMapping
    public User getExistingUser(@RequestParam String name, @RequestParam String email){
        return UserService.getExistingUser(name, email);
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
