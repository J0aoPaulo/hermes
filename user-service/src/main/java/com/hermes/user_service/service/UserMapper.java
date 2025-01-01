package com.hermes.user_service.service;

import com.hermes.user_service.controller.dto.CreateUserRequest;
import com.hermes.user_service.entity.User;
import com.hermes.user_service.entity.enums.Role;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User toUser(CreateUserRequest request) {
        return User.builder()
                .id(null)
                .name(request.name())
                .email(request.email())
                .role(Role.CLIENTE)
                .build();
    }
}
