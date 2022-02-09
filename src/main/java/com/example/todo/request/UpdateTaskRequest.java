package com.example.todo.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Model that represents a request information for a particular task to be updated.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTaskRequest {

    @NotNull(message = "id is required")
    @Min(value = 1, message = "invalid value for id")
    private Long id;

    private String title;
    private String description;
    private Long points;
    private String status;
    private LocalDateTime completionDate;
}
