package com.hermes.user_service.controller.dto;

import com.hermes.user_service.entity.User;
import com.hermes.user_service.entity.enums.Role;

public record ResponseUserDto(String name, String email, Role role) {

    public ResponseUserDto(User user) {
        this(user.getName(), user.getEmail(), user.getRole());
    }
}
