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
        var allTickets = service.findAllTickets();
        return ResponseEntity.ok(allTickets);
    }

    @GetMapping("/open")
    public ResponseEntity<List<TicketResponse>> findAllOpenTickets() {
        var openTickets = service.findAllOpenTickets();
        return ResponseEntity.ok(openTickets);
    }

    @PutMapping("/close/{ticket-id}")
    @Transactional
    public ResponseEntity<String> disableTicket(@PathVariable("ticket-id") UUID ticketId) {
        service.closeTicket(ticketId);
        return ResponseEntity.ok("Ticket closed successfully!");
    }

    @PutMapping("/assign/{ticket-id}")
    @Transactional
    public ResponseEntity<TicketResponse> allocateTicket(@PathVariable("ticket-id") UUID ticketId) {
        var allocatedTicket = service.allocateTicket(ticketId);
        return ResponseEntity.ok(allocatedTicket);
    }

    @PutMapping("/priority/{ticket-id}")
    @Transactional
    ResponseEntity<String> updateTicketPriority(@PathVariable("ticket-id") UUID ticketId, @RequestParam TicketPriority priority) {
        service.updateTicketPriority(ticketId, priority);
        return ResponseEntity.ok(String.format("Ticket with id: %s update successfully!", ticketId));
    }

    @DeleteMapping("/{ticket-id}")
    @Transactional
    public ResponseEntity<Void> deleteTicket(@PathVariable("ticket-id") UUID ticketId) {
        service.deleteTicket(ticketId);
        return ResponseEntity.noContent().build();
    }
}