package com.hermes.ticket_service.ticket;

import com.hermes.ticket_service.dto.CreateTicketRequest;
import com.hermes.ticket_service.dto.TicketResponse;
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
                .prioridade(TicketPriority.NAO_DEFINIDA)
                .status(TicketStatus.ABERTO)
                .createAt(LocalDateTime.now())
                .build();
    }

    public TicketResponse fromTicket(Ticket ticket) {
        return new TicketResponse(
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getCategoria(),
                ticket.getPrioridade(),
                ticket.getStatus(),
                ticket.getCreateAt()
        );
    }
}
