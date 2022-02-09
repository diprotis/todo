package com.example.todo.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * Model that represents a request information for a particular task to be added.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddTaskRequest {

    @NotNull(message = "personId is required")
    @Min(value = 1, message = "invalid value for personId")
    private Long personId;

    @NotEmpty(message = "title is required")
    private String title;

    @NotEmpty(message = "description is required")
    private String description;

    @NotNull(message = "points is required")
    @Min(value = 1, message = "minimum 1 point required")
    private Long points;

    @NotNull(message = "completionDate is required")
    private LocalDateTime completionDate;
}
