package com.hermes.ticket_service.ticket;

import com.hermes.ticket_service.dto.request.CreateTicketRequest;
import com.hermes.ticket_service.dto.response.TicketResponse;
import com.hermes.ticket_service.enums.TicketPriority;
import com.hermes.ticket_service.enums.TicketStatus;
import com.hermes.ticket_service.exception.TicketNotFoundException;
import com.hermes.ticket_service.user.UserClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private final TicketRepository repository;
    private final UserClient userClient;
    private final TicketMapper mapper;

    public TicketService(TicketRepository repository, TicketMapper mapper, UserClient userClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.userClient = userClient;
    }

    public UUID createTicket(CreateTicketRequest request) {
        var user = Optional.of(userClient.findUserById(request.userId()))
                .orElseThrow(() -> new NoSuchElementException("User not with this id not found"));

        var ticket =  mapper.toTicket(request);

        repository.save(ticket);
        return ticket.getId();
    }

    public List<TicketResponse> findAllTickets() {
        return repository.findAll()
                .stream()
                .map(this.mapper::fromTicket)
                .collect(Collectors.toList());
    }

    public Ticket updateTicketStatus(UUID ticketId) {
        var ticket = repository.findTicketById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket with id " + ticketId + " not found"));

        ticket.setStatus(TicketStatus.FECHADO);
        repository.save(ticket);
        return ticket;
    }

    public void deleteTicket(UUID ticketId) {
        if(!repository.existsTicketById(ticketId))
            throw new TicketNotFoundException(String.format("Ticket with id: %s not found", ticketId));

        repository.deleteTicketById(ticketId);
    }

    public void updateTicketPriority(UUID ticketId, TicketPriority priority) {
        var ticket = repository.findTicketById(ticketId)
                .orElseThrow(() -> new TicketNotFoundException("Ticket with id " + ticketId + " not found"));

        ticket.setPrioridade(priority);
        repository.save(ticket);
    }
}