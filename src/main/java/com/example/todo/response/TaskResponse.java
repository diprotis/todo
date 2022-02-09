package com.example.todo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Model that represents the task details.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private String title;
    private String description;
    private String status;
    private Long points;
    private PersonResponse personResponse;
    private LocalDateTime completionDate;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
}
