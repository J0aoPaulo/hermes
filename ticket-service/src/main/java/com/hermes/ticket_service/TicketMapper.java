package com.hermes.ticket_service;

import com.hermes.ticket_service.dto.CreateTicketRequest;
import com.hermes.ticket_service.enums.TicketPriority;
import com.hermes.ticket_service.enums.TicketStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TicketMapper {

    public Ticket toTicket(CreateTicketRequest request, UUID userId) {
        return Ticket.builder()
                .id(null)
                .userId(userId)
                .title(request.title())
                .description(request.description())
                .categoria(request.category())
                .prioridade(TicketPriority.BAIXA)
                .status(TicketStatus.ABERTO)
                .createAt(LocalDateTime.now())
                .build();
    }
}
