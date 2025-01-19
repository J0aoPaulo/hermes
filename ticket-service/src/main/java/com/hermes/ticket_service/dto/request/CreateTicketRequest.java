package com.hermes.ticket_service.dto.request;

import com.hermes.ticket_service.enums.TicketCategory;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CreateTicketRequest(
        @NotBlank(message = "required.title")
        String title,
        @NotBlank(message = "required.description")
        String description,
        @NotNull(message = "required.category")
        @Enumerated(EnumType.STRING)
        TicketCategory category,
        @NotNull
        UUID userId
) {
}
