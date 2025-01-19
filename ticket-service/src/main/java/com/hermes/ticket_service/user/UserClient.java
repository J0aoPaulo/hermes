package com.hermes.ticket_service.user;

import lombok.NonNull;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
import java.util.UUID;

@FeignClient(
        name = "user-service"
)
public interface UserClient {

    @GetMapping("/{user-id}")
    @NonNull
    UserResponse findUserById(@PathVariable("user-id") UUID userId);
}
