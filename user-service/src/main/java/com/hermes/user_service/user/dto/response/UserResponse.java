package com.hermes.user_service.user.dto.response;

import com.hermes.user_service.user.User;
import com.hermes.user_service.user.enums.Role;

public record UserResponse(String name, String email, Role role, boolean active) {

    public UserResponse(User user) {
        this(user.getName(), user.getEmail(), user.getRole(), user.getActive());
    }
}