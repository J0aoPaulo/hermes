package com.hermes.user_service.service;

import com.hermes.user_service.controller.dto.CreateUserDto;
import com.hermes.user_service.controller.dto.UpdateUserDto;
import com.hermes.user_service.entity.User;
import com.hermes.user_service.entity.enums.Role;
import com.hermes.user_service.exceptions.UserAlreadyExist;
import com.hermes.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UUID createUser(CreateUserDto userDto) {
        var emailExist = userRepository.existsByEmail(userDto.email());

        if(emailExist)
            throw new UserAlreadyExist("User already exist in database");

        var user = new User(
                null,
                userDto.name(),
                userDto.password(),
                userDto.email(),
                Role.CLIENTE
        );
        userRepository.save(user);
        return user.getId();
    }

    public User updateUser(UUID userId, UpdateUserDto userDto) {
        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NoSuchElementException("No user found with id" + userId));

        var name = Optional.ofNullable(userDto.name()).orElse(user.getName());
        var password = Optional.ofNullable(userDto.password()).orElse(user.getPassword());
        var email = Optional.ofNullable(userDto.email()).orElse(user.getEmail());

        user.setName(name);
        user.setPassword(password);
        user.setEmail(email);
        userRepository.save(user);

        return user;
    }

    public User getUserById(UUID userId) {
        var user = userRepository
                .findById(userId)
                .orElseThrow(() -> new NoSuchElementException("User with " + userId + " not found."));

        if (!user.getActive())
            throw new NoSuchElementException("Author is disabled");

        return user;
    }
}
