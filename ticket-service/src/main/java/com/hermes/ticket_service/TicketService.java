package com.hermes.ticket_service.service;

import com.hermes.ticket_service.controller.dto.CreateTicketDto;
import com.hermes.ticket_service.entity.Ticket;
import com.hermes.ticket_service.exception.TicketAlreadyExist;
import com.hermes.ticket_service.repository.TicketRepository;
import org.springframework.stereotype.Service;

import java.nio.file.FileAlreadyExistsException;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public boolean ticketExist(CreateTicketDto ticketDto) {
        return ticketRepository.existsByTitle(ticketDto.title())
                && ticketRepository.existsByDescription(ticketDto.description());
    }

    public Long createTicket(CreateTicketDto ticketDto) {
        if(ticketExist(ticketDto))
            throw new TicketAlreadyExist("Ticket already exist in database");

        return null;
    }
}
