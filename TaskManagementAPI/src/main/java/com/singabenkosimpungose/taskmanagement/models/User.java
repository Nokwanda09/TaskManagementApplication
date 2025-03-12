package com.singabenkosimpungose.taskmanagement.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;


@Entity
@Table(name="Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @NotNull
    @Column(name="username", unique=true)
    private String username;

    @NotNull
    private String password;

    @OneToMany(mappedBy="user", cascade= CascadeType.ALL, orphanRemoval= true)
    private List<Task> tasks;
}
