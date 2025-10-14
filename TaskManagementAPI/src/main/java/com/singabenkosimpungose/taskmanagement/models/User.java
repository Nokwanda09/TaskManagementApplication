package com.singabenkosimpungose.taskmanagement.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

/*
 * Represents user in the system
 * 
 * @Entity -  marks this class as a JPA entity
 * @Table(name="Users" - maps the entity to the table called "users")
 * @Data - generates getters, setters, equals, hashCode, and toString
 * @AllArgsContructor and @NoArgsConstructor - provides contructors
 * 
 */
@Entity
@Table(name="Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /*
     * Unique identifier for the user
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    /*
     * The user's full name
     */
    private String fullName;

    /*
     * The unique username for the user
     */
    @NotNull
    @Column(name="username", unique=true)
    private String username;

    /*
     * The user's password
     */
    @NotNull
    private String password;

    /*
     * List of tasks that belong to a single user
     */
    @OneToMany(mappedBy="user", cascade= CascadeType.ALL, orphanRemoval= true)
    @JsonIgnore
    private List<Task> tasks;
}
