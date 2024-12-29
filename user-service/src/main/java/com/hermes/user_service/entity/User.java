package com.hermes.user_service.entity;

import com.hermes.user_service.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "users")
@NoArgsConstructor
@EqualsAndHashCode
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    @NonNull
    private String name;

    @Column(nullable = false)
    @NonNull
    private String password;

    @Column(nullable = false)
    @NonNull
    private String email;

    @Enumerated(EnumType.STRING)
    @NonNull
    private Role role;

    private Boolean active = true;

    public User(UUID id, @NonNull String name, @NonNull String password, @NonNull String email, @NonNull Role role) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public UUID getId() {
        return id;
    }

    public @NonNull String getName() {
        return name;
    }

    public @NonNull String getPassword() {
        return password;
    }

    public @NonNull String getEmail() {
        return email;
    }

    public @NonNull Role getRole() {
        return role;
    }

    public Boolean getActive() {
        return active;
    }
}