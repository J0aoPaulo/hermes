package com.hermes.user_service.user.dto.request;

import com.hermes.user_service.user.User;

public record UpdateUserRequest(
        String name,
        String password,
        String email
) {
    public UpdateUserRequest(User user) {
        this(user.getName(), user.getPassword(), user.getEmail());
    }
}