package com.hermes.ticket_service.repository;

import com.hermes.ticket_service.entity.Ticket;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Boolean existsByTitle(@NonNull String title);

    Boolean existsByDescription(@NonNull String description);
}
