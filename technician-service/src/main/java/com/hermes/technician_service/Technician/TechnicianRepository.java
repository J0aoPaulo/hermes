package com.hermes.technician_service.Technician;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TechnicianRepository extends JpaRepository<Technician, UUID> {
    boolean existsByEmail(@NonNull String email);
}
