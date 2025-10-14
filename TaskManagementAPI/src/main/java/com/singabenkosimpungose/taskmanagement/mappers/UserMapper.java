package com.singabenkosimpungose.taskmanagement.mappers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.singabenkosimpungose.taskmanagement.DTOs.UserDTO;
import com.singabenkosimpungose.taskmanagement.models.User;
import com.singabenkosimpungose.taskmanagement.repositories.UserRepository;

/*
 * Responsible for converting between UseDto and User classes
 */
public class UserMapper {

    @Autowired
    UserRepository userRepository;

    /*
    Converts UserDto to User object

    @param UserDto
    @return User
     */
    public User convertDTOToUser(UserDTO userDto){

        Optional<User> user = userRepository.findByUsername(userDto.getUsername());

        return (User) user.get();
    }


}
