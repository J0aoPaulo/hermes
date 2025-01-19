package com.hermes.user_service.user;

import com.hermes.user_service.exception.UserNotFound;
import com.hermes.user_service.user.dto.request.CreateUserRequest;
import com.hermes.user_service.user.dto.request.UpdateUserRequest;
import com.hermes.user_service.user.dto.response.UserResponse;
import com.hermes.user_service.exception.UserAlreadyExist;
import com.hermes.user_service.user.enums.Role;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public UUID createUser(CreateUserRequest request, Role role) {
        if(repository.existsByEmail(request.email()))
            throw new UserAlreadyExist("User already exist in database");

        var user = repository.save(mapper.toUser(request, role));
        return user.getId();
    }

    private void setUser(User user, UpdateUserRequest request) {
        mergeCustomer(user, request);
        repository.save(user);
    }

    private void mergeCustomer(User user, UpdateUserRequest request) {
        if(StringUtils.isNotBlank(request.name())) user.setName(request.name());

        if(StringUtils.isNotBlank(request.password())) user.setPassword(request.password());

        if(StringUtils.isNotBlank(request.email())) user.setEmail(request.email());
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