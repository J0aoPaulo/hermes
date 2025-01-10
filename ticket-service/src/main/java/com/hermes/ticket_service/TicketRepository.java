package com.hermes.ticket_service;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Boolean existsByTitle(@NonNull String title);

    Boolean existsByDescription(@NonNull String description);

    Optional<Ticket> findTicketById(@NonNull UUID id);
}
