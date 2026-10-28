package com.xworkz.inventory.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginDto {
    private int id;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Enter a valid email address")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must contain at least 8 characters")
    private String password;
}