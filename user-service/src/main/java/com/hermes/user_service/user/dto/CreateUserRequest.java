package com.hermes.user_service.user.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record CreateUserRequest(
        @NotEmpty(message = "{required.name}")
        String name,
        @NotEmpty(message = "{required.password}")
        String password,
        @NotEmpty(message = "{required.email}")
        @Email
        String email
)
{}
