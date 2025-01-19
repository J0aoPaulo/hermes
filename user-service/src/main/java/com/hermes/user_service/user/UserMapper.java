package com.hermes.user_service.user;

import com.hermes.user_service.user.dto.request.CreateUserRequest;
import com.hermes.user_service.user.dto.response.UserResponse;
import com.hermes.user_service.user.enums.Role;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public User toUser(CreateUserRequest request) {
        return User.builder()
                .id(null)
                .name(request.name())
                .email(request.email())
                .role(Role.CUSTOMER)
                .build();
    }

    public UserResponse fromUser(User user) {
        return new UserResponse(
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getActive());
    }
}
