package com.hermes.technician_service.dto.response;

import com.hermes.technician_service.enums.ExperienceLevel;
import com.hermes.technician_service.enums.Specialization;

import java.util.UUID;

public record TechnicianResponse(
        UUID id,
        String name,
        String email,
        Specialization specialization,
        ExperienceLevel experienceLevel,
        boolean availability,
        boolean active
) {
}
