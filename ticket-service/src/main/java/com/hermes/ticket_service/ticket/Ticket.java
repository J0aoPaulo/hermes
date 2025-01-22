package com.hermes.ticket_service.ticket;

import com.hermes.ticket_service.enums.TicketCategory;
import com.hermes.ticket_service.enums.TicketPriority;
import com.hermes.ticket_service.enums.TicketStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tickets")
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @NonNull
    private UUID technicianId;

    @Column(nullable = false)
    @NonNull
    private String title;

    @Column(nullable = false)
    @NonNull
    private String description;

    @Enumerated(EnumType.STRING)
    @NonNull
    private TicketCategory category;

    @Enumerated(EnumType.STRING)
    @NonNull
    private TicketPriority priority;

    @Enumerated(EnumType.STRING)
    @NonNull
    private TicketStatus status;

    @Column(nullable = false)
    @NotNull
    private LocalDateTime createAt;
}
