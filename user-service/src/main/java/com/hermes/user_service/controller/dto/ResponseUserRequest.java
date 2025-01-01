package com.hermes.user_service.controller.dto;

import com.hermes.user_service.entity.User;
import com.hermes.user_service.entity.enums.Role;

public record ResponseUserRequest(String name, String email, Role role) {

    public ResponseUserRequest(User user) {
        this(user.getName(), user.getEmail(), user.getRole());
    }
}
