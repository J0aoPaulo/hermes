package com.hermes.ticket_service.ticket;

import com.hermes.ticket_service.dto.request.CreateTicketRequest;
import com.hermes.ticket_service.dto.response.TicketResponse;
import com.hermes.ticket_service.enums.TicketPriority;
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

    @PostMapping
    @Transactional
    public ResponseEntity<Void> createTicket(@RequestBody @Valid CreateTicketRequest request) {

        UUID ticketId = service.createTicket(request);
        return ResponseEntity.created(URI.create("/api/v1/tickets/" + ticketId)).build();
    }

    @GetMapping()
    public ResponseEntity<List<TicketResponse>> findAllTickets() {
        return ResponseEntity.ok(service.findAllTickets());
    }

    @PutMapping("/{ticket-id}")
    @Transactional
    public ResponseEntity<Ticket> disableTicket(@PathVariable("ticket-id") UUID ticketId) {
        Ticket ticketUpdated = service.closeTicket(ticketId);

        return ResponseEntity.ok(ticketUpdated);
    }

    @PatchMapping("/{ticket-id}/priority")
    @Transactional
    ResponseEntity<String> updateTicketPriority(@PathVariable("ticket-id") UUID ticketId, @RequestParam TicketPriority priority) {
        service.updateTicketPriority(ticketId, priority);

        return ResponseEntity.ok(String.format("Ticket with id: %s update successfully", ticketId));
    }

    @DeleteMapping("/{ticket-id}")
    @Transactional
    public ResponseEntity<Void> deleteTicket(@PathVariable("ticket-id") UUID ticketId) {
        service.deleteTicket(ticketId);

        return ResponseEntity.noContent().build();
    }
}