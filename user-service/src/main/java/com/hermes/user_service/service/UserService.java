package com.hermes.user_service.service;

import com.hermes.user_service.controller.dto.CreateUserRequest;
import com.hermes.user_service.controller.dto.UpdateUserRequest;
import com.hermes.user_service.entity.User;
import com.hermes.user_service.exceptions.UserAlreadyExist;
import com.hermes.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public UserService(UserRepository userRepository, UserMapper mapper) {
        this.repository = userRepository;
        this.mapper = mapper;
    }

    public UUID createUser(CreateUserRequest request) {
        if(repository.existsByEmail(request.email()))
            throw new UserAlreadyExist("User already exist in database");

        var user = repository.save(mapper.toUser(request));
        return user.getId();
    }

    private void setUser(User user, UpdateUserRequest request) {
        var name = Optional.ofNullable(request.name()).orElse(user.getName());

        var password = Optional.ofNullable(request.password()).orElse(user.getPassword());

        var email = Optional.ofNullable(request.email()).orElse(user.getEmail());

        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        repository.save(user);
    }

    public User updateUser(UUID userId, UpdateUserRequest request) {
        var user = repository
                .findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with " + userId + " not found."));

        setUser(user, request);
        return user;
    }

    public User getUserById(UUID userId) {
        var user = repository
                .findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with " + userId + " not found."));

        if (!user.getActive())
            throw new NoSuchElementException("User is disabled");

        return user;
    }
}
