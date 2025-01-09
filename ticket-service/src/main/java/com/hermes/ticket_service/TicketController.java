package com.hermes.ticket_service;

import com.hermes.ticket_service.dto.CreateTicketRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @PostMapping("/{userId}")
    @Transactional
    public ResponseEntity<Void> createTicket(@RequestBody @Valid CreateTicketRequest request,
                                             @PathVariable UUID userId) {

        UUID ticketId = service.createTicket(request, userId);
        return ResponseEntity.created(URI.create("/api/v1/tickets/" + ticketId)).build();
    }
}
