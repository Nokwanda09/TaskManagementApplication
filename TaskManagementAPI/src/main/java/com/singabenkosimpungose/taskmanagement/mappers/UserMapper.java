package com.singabenkosimpungose.taskmanagement.mappers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.singabenkosimpungose.taskmanagement.DTOs.UserDTO;
import com.singabenkosimpungose.taskmanagement.models.User;
import com.singabenkosimpungose.taskmanagement.repositories.UserRepository;


public class UserMapper {

    @Autowired
    UserRepository userRepository;

    public User convertDTOToUser(UserDTO userDto){

        Optional<User> user = userRepository.findByUsername(userDto.getUsername());

        return (User) user.get();
    }


}
