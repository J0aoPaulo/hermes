package com.hermes.user_service.user;

import com.hermes.user_service.exception.UserNotFound;
import com.hermes.user_service.user.dto.CreateUserRequest;
import com.hermes.user_service.user.dto.UpdateUserRequest;
import com.hermes.user_service.user.dto.UserResponse;
import com.hermes.user_service.exception.UserAlreadyExist;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
                .orElseThrow(() -> new UserNotFound("User with " + userId + " not found."));

        setUser(user, request);
        return user;
    }

    public User getUserById(UUID userId) {
        var user = repository
                .findById(userId)
                .orElseThrow(() -> new UserNotFound("User with " + userId + " not found."));

        if (!user.getActive())
            throw new UserNotFound("User is disabled");

        return user;
    }

    public List<UserResponse> findAllUsers() {
        return this.repository.findAll()
                .stream()
                .map(this.mapper::fromUser)
                .collect(Collectors.toList());
    }
}