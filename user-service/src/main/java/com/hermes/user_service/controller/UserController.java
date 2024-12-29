package com.hermes.user_service.controller;

import com.hermes.user_service.controller.dto.CreateUserDto;
import com.hermes.user_service.controller.dto.ResponseUserDto;
import com.hermes.user_service.controller.dto.UpdateUserDto;
import com.hermes.user_service.repository.UserRepository;
import com.hermes.user_service.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> createUser(@RequestBody @Valid CreateUserDto createUserDto) {
        var userId = userService.createUser(createUserDto);

        return ResponseEntity.created(URI.create("/api/v1/users/" + userId)).build();
    }

    @PutMapping("/{userId}")
    @Transactional
    public ResponseEntity<UpdateUserDto> updateUser(@PathVariable("userId") UUID userId,
                                                    @RequestBody @Valid UpdateUserDto updateUserDto) {

        var userUpdated = userService.updateUser(userId, updateUserDto);

        return ResponseEntity.ok(new UpdateUserDto(userUpdated));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseUserDto> getUser(@PathVariable("userId") UUID userId) {
        var user = userService.getUserById(userId);

        return ResponseEntity.ok(new ResponseUserDto(user));
    }

    @DeleteMapping("/{userId}")
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") UUID userId) {
        if(userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }
}
