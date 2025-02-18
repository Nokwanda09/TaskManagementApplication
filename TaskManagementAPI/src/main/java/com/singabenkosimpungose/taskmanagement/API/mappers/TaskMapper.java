package com.singabenkosimpungose.taskmanagement.API.mappers;

import com.singabenkosimpungose.taskmanagement.API.DTOs.TaskDTO;
import com.singabenkosimpungose.taskmanagement.API.models.Category;
import com.singabenkosimpungose.taskmanagement.API.models.Task;
import com.singabenkosimpungose.taskmanagement.API.models.User;
import com.singabenkosimpungose.taskmanagement.API.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    @Autowired
    private UserService userService;


    public Task toTaskEntity(TaskDTO taskDTO){
        User user = userService.findUserById(taskDTO.getUserId());
        Task task = new Task();
        Category category = Category.valueOf(taskDTO.getCategory().toUpperCase());


        task.setName(taskDTO.getName());
        task.setNotes(taskDTO.getNotes());
        task.setCategory(category);
        task.setDueDate(new DateFormatter().toLocalDate(taskDTO.getDueDate()));
        task.setUser(user);
        return  task;
    }


    public TaskDTO toTaskDTO(Task task){
        TaskDTO taskDTO = new TaskDTO();
        Long userId = task.getUser().getId();

        taskDTO.setName(task.getName());
        taskDTO.setNotes(task.getNotes());
        taskDTO.setCategory(task.getCategory().toString());
        taskDTO.setDueDate(task.getDueDate().toString());
        taskDTO.setUserId(userId);

        return taskDTO;
    }


}