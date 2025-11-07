package com.singabenkosimpungose.taskmanagement.repositories;

import com.singabenkosimpungose.taskmanagement.models.Task;
import com.singabenkosimpungose.taskmanagement.models.Category;
import com.singabenkosimpungose.taskmanagement.models.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

import java.time.LocalDate; 


public interface TaskRepository extends JpaRepository<Task, Long>{

    public Optional<Task> findByNameIgnoreCase(String name);


    public List<Task> findAllByCategoryAndUser(Category category, User user);

    public List<Task> findAllByDueDateAndUser(LocalDate dueDate, User user);

    @Query(value = "SELECT * FROM TASKS WHERE name=:name AND user_id=:id LIMIT 1", nativeQuery = true)
    public Optional<Task> findByNameAndUserId(@Param("name") String name,@Param("id") Long id);

    public List<Task> findByUser(User user);
    public List<Task> findAllByUser(User user);
}
