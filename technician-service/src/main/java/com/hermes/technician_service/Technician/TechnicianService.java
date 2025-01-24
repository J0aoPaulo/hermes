package com.hermes.technician_service.Technician;

import com.hermes.technician_service.dto.request.CreateTechnicianRequest;
import com.hermes.technician_service.dto.request.UpdateTechnicianRequest;
import com.hermes.technician_service.dto.response.TechnicianResponse;
import com.hermes.technician_service.exceptions.TechnicianAlreadyExist;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TechnicianService {

    private final TechnicianRepository repository;
    private final TechnicianMapper mapper;

    public TechnicianService(TechnicianRepository repository, TechnicianMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UUID createTechnician(CreateTechnicianRequest request) {
        if(repository.existsByEmail((request.email())))
            throw new TechnicianAlreadyExist("Technician already exist in database");

        var technician = mapper.toTechnician(request);
        repository.save(technician);
        return technician.getId();
    }

    public TechnicianResponse updateTechnician(UUID technicianId, UpdateTechnicianRequest request) {
        var technician = repository
                .findById(technicianId)
                .orElseThrow(() -> new NoSuchElementException("Technician Already exist"));

        setTechnician(technician, request);
        repository.save(technician);
        return mapper.fromTechnician(technician);
    }

    private void setTechnician(Technician technician, UpdateTechnicianRequest request) {
        if(StringUtils.isNotBlank(request.name())) technician.setName(request.name());

        if(StringUtils.isNotBlank(request.email())) technician.setEmail(request.email());

        if(StringUtils.isNotBlank(request.password())) technician.setPassword(request.password());

        if(request.specialization() != null) technician.setSpecialization(request.specialization());

        if(request.experienceLevel() != null) technician.setExperienceLevel(request.experienceLevel());
    }

    public List<TechnicianResponse> findAllTechnicians() {
        return repository.findAll()
                .stream()
                .map(this.mapper::fromTechnician)
                .collect(Collectors.toList());
    }

    public List<TechnicianResponse> findAllActiveTechnicians() {
        return repository.findAll()
                .stream()
                .filter(Technician::isActive)
                .map(this.mapper::fromTechnician)
                .collect(Collectors.toList());
    }

    public void deleteTechnician(UUID technicianId) {
        if(!repository.existsById(technicianId))
            throw new NoSuchElementException(String.format("Technician with id %s not found", technicianId));

        repository.deleteById(technicianId);
    }
}
