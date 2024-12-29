package com.hermes.ticket_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tickets")
@EqualsAndHashCode
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userId;
}