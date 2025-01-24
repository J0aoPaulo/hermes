package com.hermes.technician_service.dto.request;

import com.hermes.technician_service.enums.ExperienceLevel;
import com.hermes.technician_service.enums.Specialization;

public record UpdateTechnicianRequest(
        String name,
        String email,
        String password,
        Specialization specialization,
        ExperienceLevel experienceLevel,
        boolean active
) {
}
