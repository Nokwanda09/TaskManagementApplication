package com.singabenkosimpungose.taskmanagement.controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.singabenkosimpungose.taskmanagement.services.TaskService;
import com.singabenkosimpungose.taskmanagement.services.UserService;
import com.singabenkosimpungose.taskmanagement.models.Task;
import com.singabenkosimpungose.taskmanagement.models.User;
import com.singabenkosimpungose.taskmanagement.DTOs.TaskDTO;

import java.util.List;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;


    @GetMapping("/get")
    public ResponseEntity<List<TaskDTO>> getTasks(@RequestParam(required= false) String dueDate,
    @RequestParam(required=false) String category,
    @RequestParam Long userId){

        List<TaskDTO> tasks;

        if (dueDate != null){
            tasks =  taskService.getAllTasksByDueDate(dueDate, userId);
        }else if(category != null){
            tasks = taskService.getAllTasksInCategory(category, userId);
        }else{
            tasks = taskService.getAllTaskForUser(userId);
        }

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }


    @GetMapping("/search")
    public ResponseEntity<Task> getTaskByName(@RequestParam String name){
        return new ResponseEntity<>(taskService.getTaskByName(name), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks(){

        if (taskService.getAllTasks().isEmpty()) return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }



    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody TaskDTO task){
        return new ResponseEntity<>(taskService.addNewTask(task), HttpStatus.OK);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
            taskService.deleteTask(id);
            return new ResponseEntity<>(HttpStatus.OK);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id,@Valid @RequestBody Task task){
            return new ResponseEntity<>(taskService.updateTask(id, task), HttpStatus.OK);
    }
    

}
