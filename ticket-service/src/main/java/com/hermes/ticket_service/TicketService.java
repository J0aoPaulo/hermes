package com.hermes.ticket_service;

import com.hermes.ticket_service.dto.CreateTicketRequest;
import com.hermes.ticket_service.exception.TicketAlreadyExist;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
}
