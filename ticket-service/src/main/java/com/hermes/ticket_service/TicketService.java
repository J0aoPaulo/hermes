package com.hermes.ticket_service;

import com.hermes.ticket_service.dto.CreateTicketRequest;
import com.hermes.ticket_service.dto.TicketResponse;
import com.hermes.ticket_service.enums.TicketStatus;
import com.hermes.ticket_service.exception.TicketAlreadyExist;
import com.hermes.ticket_service.exception.TicketNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository repository;
    private final TicketMapper mapper;

    public TicketService(TicketRepository repository, TicketMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public UUID createTicket(CreateTicketRequest request, UUID userId) {
        var ticket =  mapper.toTicket(request, userId);

        repository.save(ticket);
        return ticket.getId();
    }

    public List<TicketResponse> findAllTickets() {
        return repository.findAll()
                .stream()
                .map(this.mapper::fromTicket)
                .collect(Collectors.toList());
    }

    public Ticket updateTicket(UUID ticketId) {
        var ticket = repository.findTicketById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket with id " + ticketId + " not found"));

        ticket.setStatus(TicketStatus.FECHADO);
        return ticket;
    }
}
