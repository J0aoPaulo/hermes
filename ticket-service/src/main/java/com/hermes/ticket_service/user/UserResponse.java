package com.hermes.ticket_service.user;

import java.util.UUID;

public record UserResponse(
        UUID userId,
        String name,
        String email,
        boolean active
) {
}
