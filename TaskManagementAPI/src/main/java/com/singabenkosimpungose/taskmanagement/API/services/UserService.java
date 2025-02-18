package com.singabenkosimpungose.taskmanagement.API.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.singabenkosimpungose.taskmanagement.API.repositories.UserRepository;
import com.singabenkosimpungose.taskmanagement.API.models.User;
import com.singabenkosimpungose.taskmanagement.API.exceptions.EntityNotFoundException;

import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    public User findUserById(Long id){

        Optional<User> user = userRepository.findById(id);

        if (user.isPresent()){
            return (User) user.get();
        } else{
            throw new EntityNotFoundException("User does not exist");
        }

    }


    public User createUser(User user){
        return userRepository.save(user);
    }


    public User getExistingUser(String name, String email){
        Optional<User> user = userRepository.findUserByNameAndEmail(name, email);

        if (user.isPresent()){
            return (User) user.get();
        } else{
            throw new EntityNotFoundException("The user does not exist");
        }
    }


    public void deleteUser(String name, String email){
        Optional<User> user = userRepository.findUserByNameAndEmail(name, email);

        if (user.isPresent()){
            userRepository.delete((User) user.get());
        } else{
            throw new EntityNotFoundException("The user does not exist");
        }
    }




}
