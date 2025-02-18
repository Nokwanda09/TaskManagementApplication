package com.singabenkosimpungose.taskmanagement.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import java.time.LocalDate;


@Entity
@Table(name="Tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @NotNull
    @NotEmpty
    private String name;

    private String notes;  //Additional notes for the task

    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private Category category;


    @ManyToOne
    @JoinColumn(name= "user_id", nullable = false)
    private User user;
}
  