package com.hermes.technician_service.Technician;

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
@EqualsAndHashCode
@Getter
@Setter
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NonNull
    @Column(nullable = false)
    private String name;

    @NonNull
    @Column(nullable = false)
    private String email;

    @NonNull
    @Column(nullable = false)
    private String password;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Specialization specialization;

    @NonNull
    @Column(nullable = false)
    private boolean availability;

    @NonNull
    @Column(nullable = false)
    private ExperienceLevel experienceLevel;

    @ElementCollection
    @CollectionTable(name = "technician_tickets", joinColumns = @JoinColumn(name = "technician_id"))
    @Column(name = "ticket_id")
    private List<UUID> currentTicketIds = new ArrayList<>();

    @NonNull
    @Column(nullable = false)
    private boolean active;

    @Builder
    public Technician(UUID id, String name, String email, String password, Specialization specialization,
                      boolean availability, ExperienceLevel experienceLevel, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.specialization = specialization;
        this.availability = availability;
        this.experienceLevel = experienceLevel;
        this.active = active;
    }
}
