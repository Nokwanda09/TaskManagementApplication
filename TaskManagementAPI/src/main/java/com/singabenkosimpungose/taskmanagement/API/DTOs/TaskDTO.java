package com.singabenkosimpungose.taskmanagement.API.DTOs;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {

    private String name;

    private String notes;

    private String category;

    private String dueDate;

    private Long userId;

}
