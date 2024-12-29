package com.hermes.user_service.controller.dto;

import com.hermes.user_service.entity.User;

public record UpdateUserDto (
        String name,
        String password,
        String email
) {
    public UpdateUserDto(User user) {
        this(user.getName(), user.getPassword(), user.getEmail());
    }
}