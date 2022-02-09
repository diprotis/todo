package com.example.todo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Model that contains the request details for Update Person.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePersonRequest {

    @NotNull(message = "id is required")
    @Min(value = 1, message = "invalid value for id")
    private Long id;

    private String name;
    private String email;
    private String password;
}
