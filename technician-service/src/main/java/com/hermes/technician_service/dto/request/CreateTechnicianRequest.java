package com.hermes.technician_service.dto.request;

import com.hermes.technician_service.enums.ExperienceLevel;
import com.hermes.technician_service.enums.Specialization;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateTechnicianRequest(
        @NotBlank(message = "required.name")
        String name,
        @NotBlank(message = "required.email")
        @Email
        String email,
        @NotBlank(message = "required.password")
        String password,
        @NotNull(message = "required.specialization")
        Specialization specialization,
        @NotNull(message = "required.experience")
        ExperienceLevel experienceLevel
) {
}
