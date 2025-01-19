package com.hermes.user_service.user;

import com.hermes.user_service.exception.UserNotFound;
import com.hermes.user_service.user.dto.request.CreateUserRequest;
import com.hermes.user_service.user.dto.request.UpdateUserRequest;
import com.hermes.user_service.user.dto.response.UserResponse;
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
                .orElseThrow(() -> new UserNotFound(String.format("User with id: %s not found", userId)));;

        setUser(user, request);
        return user;
    }

    public UserResponse getUserById(UUID userId) {
        return repository
                .findById(userId)
                .filter(User::getActive)
                .map(this.mapper::fromUser)
                .orElseThrow(() -> new UserNotFound(String.format("User with id: %s not found", userId)));
    }

    public List<UserResponse> findAllUsers() {
        return this.repository.findAll()
                .stream()
                .map(this.mapper::fromUser)
                .collect(Collectors.toList());
    }

    public void deleteUser(UUID userId) {
        if(!repository.existsById(userId))
            throw new UserNotFound(String.format("User with id: %s not found", userId));

        repository.deleteById(userId);
    }
}