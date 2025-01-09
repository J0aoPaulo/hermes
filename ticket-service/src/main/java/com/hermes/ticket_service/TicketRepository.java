package com.hermes.ticket_service;

import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Boolean existsByTitle(@NonNull String title);

    Boolean existsByDescription(@NonNull String description);
}
