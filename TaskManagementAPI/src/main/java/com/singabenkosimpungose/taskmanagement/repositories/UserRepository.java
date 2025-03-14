package com.singabenkosimpungose.taskmanagement.repositories;

import com.singabenkosimpungose.taskmanagement.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{

    public Optional<User> findByUsername(String username);

    public boolean existsByUsername(String username);

    // public Optional<User> findUserByNameAndEmail(String name, String email);

    // public Optional<User> findUserByEmail(String email);
}