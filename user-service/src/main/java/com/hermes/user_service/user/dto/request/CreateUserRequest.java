package com.hermes.user_service.user.dto.request;


import com.hermes.user_service.user.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record CreateUserRequest(
        @NotBlank(message = "{required.name}")
        String name,
        @NotBlank(message = "{required.password}")
        String password,
        @NotBlank(message = "{required.email}")
        @Email
        String email
)
{}
