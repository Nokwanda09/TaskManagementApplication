package com.singabenkosimpungose.taskmanagement.API.models;

import jakarta.persistence.*;

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


    private String name;

    private String email;

    @OneToMany(mappedBy="user", cascade= CascadeType.ALL, orphanRemoval= true)
    private List<Task> tasks;
}
