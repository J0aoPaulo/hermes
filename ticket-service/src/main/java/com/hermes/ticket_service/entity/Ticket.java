package com.hermes.ticket_service.entity;

import com.hermes.ticket_service.entity.enums.TicketCategory;
import com.hermes.ticket_service.entity.enums.TicketPriority;
import com.hermes.ticket_service.entity.enums.TicketStatus;
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
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NonNull
    private UUID userId;

    @Column(nullable = false)
    @NonNull
    private String title;

    @Column(nullable = false)
    @NonNull
    private String description;

    @Enumerated(EnumType.STRING)
    @NonNull
    private TicketCategory categoria;

    @Enumerated(EnumType.STRING)
    @NonNull
    private TicketPriority prioridade;

    @Enumerated(EnumType.STRING)
    @NonNull
    private TicketStatus status;

    @Column(nullable = false)
    @NotNull
    private LocalDateTime createAt;
}
