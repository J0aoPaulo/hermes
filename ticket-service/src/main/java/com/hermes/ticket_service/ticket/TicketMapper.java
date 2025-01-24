package com.hermes.ticket_service.ticket;

import com.hermes.ticket_service.dto.request.CreateTicketRequest;
import com.hermes.ticket_service.dto.response.TicketResponse;
import com.hermes.ticket_service.enums.TicketPriority;
import com.hermes.ticket_service.enums.TicketStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TicketMapper {

    public Ticket toTicket(CreateTicketRequest request) {

        return Ticket.builder()
                .id(null)
                .technicianId(request.userId())
                .title(request.title())
                .description(request.description())
                .category(request.category())
                .priority(TicketPriority.NOT_DEFINED)
                .status(TicketStatus.ABERTO)
                .createAt(LocalDateTime.now())
                .build();
    }

    public TicketResponse fromTicket(Ticket ticket) {
        return new TicketResponse(
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getCategory(),
                ticket.getPriority(),
                ticket.getStatus(),
                ticket.getCreateAt()
        );
    }
}
