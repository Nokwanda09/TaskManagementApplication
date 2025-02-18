package com.singabenkosimpungose.taskmanagement.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.singabenkosimpungose.taskmanagement.models.Task;
import com.singabenkosimpungose.taskmanagement.models.User;
import com.singabenkosimpungose.taskmanagement.mappers.DateFormatter;
import com.singabenkosimpungose.taskmanagement.models.Category;
import com.singabenkosimpungose.taskmanagement.repositories.TaskRepository;
import com.singabenkosimpungose.taskmanagement.DTOs.TaskDTO;
import com.singabenkosimpungose.taskmanagement.mappers.TaskMapper;
import com.singabenkosimpungose.taskmanagement.exceptions.EntityNotFoundException;


import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;


    @Autowired
    private TaskMapper taskMapper;


    @Autowired
    private UserService userService;

    public Task getTaskById(Long id){
        Optional<Task> task = taskRepository.findById(id);
            if (task.isPresent()){
                return (Task) task.get();
            } else{
                throw new EntityNotFoundException("Task by that id is not found");
            }  
    }


    public Task getTaskByName(String name){
        Optional<Task> task = taskRepository.findByNameIgnoreCase(name);

            if (task.isPresent()){
                return (Task) task.get();
            } else{
                throw new EntityNotFoundException("Task by that name is not found");
            }  
    }


    public List<TaskDTO> getAllTasks(){
        List<TaskDTO> tasks = new ArrayList<>();

        taskRepository.findAll().forEach(task -> tasks.add(taskMapper.toTaskDTO(task)));

        return tasks;
    }

    public List<TaskDTO> getAllTasksInCategory(String categoryString, Long userId){
        User user = userService.findUserById(userId);
        List<TaskDTO> tasks = new ArrayList<>();
        Category category = Category.valueOf(categoryString.toUpperCase());
        taskRepository.findAllByCategoryAndUser(category, user).forEach(task -> tasks.add(taskMapper.toTaskDTO(task)));

        return tasks;
    }

    public List<TaskDTO> getAllTasksByDueDate(String dateString, Long userId){
        List<TaskDTO> tasks = new ArrayList<>();
        User user = userService.findUserById(userId);
        try {
            LocalDate date = new DateFormatter().toLocalDate(dateString);
            taskRepository.findAllByDueDateAndUser(date, user).forEach(task -> tasks.add(taskMapper.toTaskDTO(task)));
            return tasks;
        } catch (DateTimeParseException error){
            throw new IllegalArgumentException("Invalid date format. Ensure format is 'yyyy-MM-dd'");
        }
    }


    public List<TaskDTO> getAllTaskForUser(Long userId){
        List<TaskDTO> tasks = new ArrayList<>();
        User user = userService.findUserById(userId);
        taskRepository.findAllByUser(user).forEach(task -> tasks.add(taskMapper.toTaskDTO(task)));
        return tasks;
    }


    public Task addNewTask(TaskDTO newTask){

        return taskRepository.save(taskMapper.toTaskEntity( newTask));
    }


    public void deleteTask(Long id){
            Optional<Task> task = taskRepository.findById(id);

            if (task.isPresent()){
                taskRepository.delete((Task) task.get());
            } else{
                throw new EntityNotFoundException("Task by that id is not found");
            }     
    }


    public Task updateTask(Long id, Task newTaskData){

        Optional<Task> task = taskRepository.findById(id);

        if (task.isPresent()){
            Task updateData = (Task) task.get();
            updateData.setName(newTaskData.getName());
            updateData.setNotes(newTaskData.getNotes());
            updateData.setCategory(newTaskData.getCategory());
            updateData.setDueDate(newTaskData.getDueDate());

            return taskRepository.save(updateData);
        } else{
            throw new EntityNotFoundException("Book by that id is not found");
        }  

    }
}
