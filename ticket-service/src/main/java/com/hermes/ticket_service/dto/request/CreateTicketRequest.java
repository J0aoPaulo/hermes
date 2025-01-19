package com.hermes.ticket_service.dto.request;

import com.hermes.ticket_service.enums.TicketCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record CreateTicketRequest(
        @NotNull
        String title,
        @NotNull
        String description,
        @NotNull
        @Enumerated(EnumType.STRING)
        TicketCategory category
) {
}
