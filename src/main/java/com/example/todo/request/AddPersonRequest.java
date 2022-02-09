package com.example.todo.request;

import com.example.todo.constants.ApplicationConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Model that represents the request information for adding a new "Person".
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddPersonRequest {

    @NotEmpty(message = "name is required")
    private String name;

    @Email(message = "valid email required")
    @NotEmpty(message = "email is required")
    private String email;

    @NotEmpty(message = "password is required")
    @Length(min = 8, message = "minimum 8 characters required for password")
    private String password;

    private String role;
}
