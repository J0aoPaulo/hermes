package com.hermes.technician_service.Technician;

import com.hermes.technician_service.dto.request.CreateTechnicianRequest;
import com.hermes.technician_service.dto.response.TechnicianResponse;
import org.springframework.stereotype.Service;

@Service
public class TechnicianMapper {

    public Technician toTechnician(CreateTechnicianRequest request) {
        return Technician.builder()
                .id(null)
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .specialization(request.specialization())
                .availability(true)
                .experienceLevel(request.experienceLevel())
                .active(true)
                .build();
    }

    public TechnicianResponse fromTechnician(Technician technician) {
        return new TechnicianResponse(
                technician.getId(),
                technician.getName(),
                technician.getEmail(),
                technician.getSpecialization(),
                technician.getExperienceLevel(),
                technician.isAvailability(),
                technician.isActive()
        );
    }
}
