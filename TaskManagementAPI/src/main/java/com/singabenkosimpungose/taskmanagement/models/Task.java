package com.singabenkosimpungose.taskmanagement.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.*;

import java.time.LocalDate;


/*
 * Represents task in the system
 * 
 * @Entity -  marks this class as a JPA entity
 * @Table(name="Tasks" - maps the entity to the table called "tasks")
 * @Data - generates getters, setters, equals, hashCode, and toString
 * @AllArgsContructor and @NoArgsConstructor - provides contructors
 * 
 */


@Entity
@Table(name="Tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    /*
     * Unique identifier for a task
     */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    /*
     * Name for the task
     */
    @NotNull
    @NotEmpty
    private String name;


    /*
     * Extra information about the task
     */
    private String notes;  //Additional notes for the task

    /*
     * Date when the task is due
     */
    private LocalDate dueDate;

    /*
     * Where the task belong. (School, work or personal)
     */
    @Enumerated(EnumType.STRING)
    private Category category;


    /*
     * User that the task belong to
     */
    @NotNull
    @ManyToOne
    @JoinColumn(name= "user_id", nullable = false)
    private User user;
}
  