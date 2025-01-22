package com.hermes.technician_service.entity;

import com.hermes.technician_service.enums.Specialization;
import com.hermes.technician_service.enums.ExperienceLevel;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "technicians")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Builder
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Specialization specialization;

    @Column(nullable = false)
    private boolean availability;

    @Column(nullable = false)
    private ExperienceLevel experienceLevel;

    @ElementCollection
    @CollectionTable(name = "technician_tickets", joinColumns = @JoinColumn(name = "technician_id"))
    @Column(name = "ticket_id")
    private List<UUID> currentTicketIds = new ArrayList<>();

    @Column(nullable = false)
    private boolean active = true;
}
