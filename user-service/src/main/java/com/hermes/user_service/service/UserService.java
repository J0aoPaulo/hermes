package com.hermes.user_service.service;

import com.hermes.user_service.controller.dto.CreateUserDto;
import com.hermes.user_service.entity.User;
import com.hermes.user_service.entity.enums.Role;
import com.hermes.user_service.exceptions.UserAlreadyExist;
import com.hermes.user_service.repository.UserRepository;
import org.springframework.stereotype.Service;

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
}
