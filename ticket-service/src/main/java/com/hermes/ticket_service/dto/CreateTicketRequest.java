package com.hermes.ticket_service.controller.dto;

import jakarta.validation.constraints.NotNull;

public record CreateTicketDto(
        @NotNull
        String title,
        @NotNull
        String description,
        @NotNull
        String category
) {
}
