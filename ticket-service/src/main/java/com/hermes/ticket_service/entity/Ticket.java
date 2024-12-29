package com.hermes.ticket_service.entity;

import com.hermes.user_service.entity.User;
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

    @ManyToOne()
    @JoinColumn(name = "user_id")
    User user = new User();
}
