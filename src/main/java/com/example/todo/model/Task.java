package com.example.todo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Model that represents the information about a particular task.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {
    private Long id;
    private String title;
    private String description;
    private Status status;
    private Person person;
    private Long points;
    private LocalDateTime completionDate;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
