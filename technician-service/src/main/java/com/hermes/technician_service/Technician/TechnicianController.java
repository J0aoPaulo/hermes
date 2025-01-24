package com.hermes.technician_service.Technician;

import com.hermes.technician_service.dto.request.CreateTechnicianRequest;
import com.hermes.technician_service.dto.request.UpdateTechnicianRequest;
import com.hermes.technician_service.dto.response.TechnicianResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/technicians")
public class TechnicianController {

    private final TechnicianService service;

    public TechnicianController(TechnicianService service) {
        this.service = service;
    }

    @PostMapping
    @Transactional
    ResponseEntity<UUID> createTechnician(@RequestBody @Valid CreateTechnicianRequest request) {
        var technicianId = service.createTechnician(request);

        return ResponseEntity.created(URI.create("/api/v1/technicians/" + technicianId)).build();
    }

    @PutMapping("/{technician-id}")
    @Transactional
    public ResponseEntity<TechnicianResponse> updateTechnician(@PathVariable("technician-id") UUID technicianId,
                                                               @RequestBody @Valid UpdateTechnicianRequest request) {
        var updatedTechnician = service.updateTechnician(technicianId, request);
        return ResponseEntity.ok(updatedTechnician);
    }

    @GetMapping
    public ResponseEntity<List<TechnicianResponse>> findAllTechnicians() {
        var allTechnicians =  service.findAllTechnicians();
        return ResponseEntity.ok(allTechnicians);
    }

    @GetMapping("/active")
    public ResponseEntity<List<TechnicianResponse>> findActiveTechnicians() {
        var activeTechnicians = service.findAllActiveTechnicians();
        return ResponseEntity.ok(activeTechnicians);
    }

    @DeleteMapping("/{technician-id}")
    @Transactional
    ResponseEntity<Void> deleteTechnician(@PathVariable("technician-id") UUID technicianId) {
        service.deleteTechnician(technicianId);
        return ResponseEntity.noContent().build();
    }
}
