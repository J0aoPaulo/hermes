package com.hermes.ticket_service.dto.response;

import com.hermes.ticket_service.enums.TicketCategory;
import com.hermes.ticket_service.enums.TicketPriority;
import com.hermes.ticket_service.enums.TicketStatus;

import java.time.LocalDateTime;

public record TicketResponse(
    String title,
    String description,
    TicketCategory category,
    TicketPriority priority,
    TicketStatus status,
    LocalDateTime createAt
) {
}
