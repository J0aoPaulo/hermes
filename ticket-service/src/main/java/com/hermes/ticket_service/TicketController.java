package com.hermes.ticket_service;

import com.hermes.ticket_service.dto.CreateTicketRequest;
import com.hermes.ticket_service.dto.TicketResponse;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
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

    @GetMapping()
    public ResponseEntity<List<TicketResponse>> findAllTickets() {
        return ResponseEntity.ok(service.findAllTickets());
    }

    @PutMapping("/{ticketId}")
    @Transactional
    public ResponseEntity<Ticket> disableTicket(@PathVariable("ticketId") UUID ticketId) {
        Ticket ticketUpdated = service.updateTicket(ticketId);

        return ResponseEntity.ok(ticketUpdated);
    }

    @DeleteMapping("{/ticketId}")
    @Transactional
    public ResponseEntity<Void> deleteTicket(@PathVariable("ticketId") UUID ticketId) {
        service.deleteTicket(ticketId);

        return ResponseEntity.noContent().build();
    }
}
