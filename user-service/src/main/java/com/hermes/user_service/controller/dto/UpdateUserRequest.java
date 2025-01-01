package com.hermes.user_service.controller.dto;

import com.hermes.user_service.entity.User;

public record UpdateUserRequest(
        String name,
        String password,
        String email
) {
    public UpdateUserRequest(User user) {
        this(user.getName(), user.getPassword(), user.getEmail());
    }
}