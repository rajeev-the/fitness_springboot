package com.fitness.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Data
public class RegisterRequest {
    @NotBlank(message = "Email is Required")
    @Email(message = "Invaild Email formate")
    private String email;
    @NotBlank(message = "Password is requied")
    @Length(min = 6, max = 6)
    private String password;
    private String firstname;
    private  String lastname;
}
