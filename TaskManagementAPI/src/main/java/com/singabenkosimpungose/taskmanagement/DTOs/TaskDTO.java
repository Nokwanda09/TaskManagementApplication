package com.singabenkosimpungose.taskmanagement.DTOs;

import lombok.*;

/**
 * Represents the Task object details put by the user.
    @param name - name of the task
    @param notes - extra info about the tasks
    @param category - specify if the task is personal, work, or school related
    @param dueDate - the date the task is due
    @param username - the username of the person entering the task

 */


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private String name;

    private String notes;

    private String category;

    private String dueDate;

    private String username;

}
