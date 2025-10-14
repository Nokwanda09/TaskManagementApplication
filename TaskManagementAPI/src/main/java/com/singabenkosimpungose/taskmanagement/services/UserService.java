package com.singabenkosimpungose.taskmanagement.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.singabenkosimpungose.taskmanagement.repositories.UserRepository;
import com.singabenkosimpungose.taskmanagement.models.User;
import com.singabenkosimpungose.taskmanagement.DTOs.UserDTO;
import com.singabenkosimpungose.taskmanagement.exceptions.EntityNotFoundException;

import java.util.Optional;

@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    // @Autowired
    // private UserDTO userDto;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public String verifyUser(UserDTO user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated())
            return jwtService.generateToken(user.getUsername());

        return "Couldn't verify user";

    }

    
    public User createUser(User user) {

        if (userRepository.existsByUsername(user.getUsername())){
            throw new DataIntegrityViolationException("Username already exists!");
        
        }
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findUserById(Long id){

        Optional<User> user = userRepository.findById(id);

        System.out.println(user);

        if (user.isPresent()){
            return (User) user.get();
        } else{
            throw new EntityNotFoundException("User does not exist");
        }

    }


    public User findUserByUsername(String username){
        Optional<User> user = userRepository.findByUsername(username);

         if (user.isPresent()){
            return (User) user.get();
        } else{
            throw new EntityNotFoundException("User does not exist");
        }
    }


    


    // public Boolean userExists(String email){
    //     Optional<User> user = userRepository.findUserByEmail(email);

    //     if (user.isPresent()){
    //         return true;
    //     } else{
    //         // throw new EntityNotFoundException("The user does not exist");
    //         return false;
    //     }
    // }


    // public void deleteUser(String name, String email){
    //     Optional<User> user = userRepository.findUserByNameAndEmail(name, email);

    //     if (user.isPresent()){
    //         userRepository.delete((User) user.get());
    //     } else{
    //         throw new EntityNotFoundException("The user does not exist");
    //     }
    // }




}
