package com.singabenkosimpungose.taskmanagement.mappers;

import com.singabenkosimpungose.taskmanagement.DTOs.TaskDTO;
import com.singabenkosimpungose.taskmanagement.models.Category;
import com.singabenkosimpungose.taskmanagement.models.Task;
import com.singabenkosimpungose.taskmanagement.models.User;
import com.singabenkosimpungose.taskmanagement.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*Responsible for converting between Task and TaskDTO classes */

@Component
public class TaskMapper {

    @Autowired
    private UserService userService;


    /*
    Converts TaskDTO to task entity
    @param TaskDTO - represents info entered by the user
    @return Task - task object that represents the task added by the user
     */
    public Task toTaskEntity(TaskDTO taskDTO){
        User user = userService.findUserByUsername(taskDTO.getUsername());
        Task task = new Task();
        Category category = Category.valueOf(taskDTO.getCategory().toUpperCase());


        if (taskDTO.getDueDate() == null){
            task.setDueDate(null);
        } else{
        task.setDueDate(new DateFormatter().toLocalDate(taskDTO.getDueDate()));
        }
        task.setName(taskDTO.getName());
        task.setNotes(taskDTO.getNotes());
        task.setCategory(category);
       
        task.setUser(user);
        return  task;
    }


    /*
    Converts the Task object into TaskDto object

    @param Task 
    @return TaskDTO
    */
    public TaskDTO toTaskDTO(Task task){
        TaskDTO taskDTO = new TaskDTO();
        // Long userId = task.getUser().getId();
        if (task.getDueDate() == null){
            task.setDueDate(null);
        }else{
        taskDTO.setDueDate(task.getDueDate().toString());
        }



        taskDTO.setName(task.getName());
        taskDTO.setNotes(task.getNotes());
        taskDTO.setCategory(task.getCategory().toString());
        taskDTO.setUsername(task.getUser().getUsername());

        return taskDTO;
    }


}