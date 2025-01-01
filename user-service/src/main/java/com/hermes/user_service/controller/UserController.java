package com.hermes.user_service.controller;

import com.hermes.user_service.controller.dto.CreateUserRequest;
import com.hermes.user_service.controller.dto.ResponseUserRequest;
import com.hermes.user_service.controller.dto.UpdateUserRequest;
import com.hermes.user_service.repository.UserRepository;
import com.hermes.user_service.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> createUser(@RequestBody @Valid CreateUserRequest request) {
        var userId = userService.createUser(request);

        return ResponseEntity.created(URI.create("/api/v1/users/" + userId)).build();
    }

    @PutMapping("/{userId}")
    @Transactional
    public ResponseEntity<UpdateUserRequest> updateUser(@PathVariable("userId") UUID userId,
                                                        @RequestBody @Valid UpdateUserRequest updateUserRequest)
    {
        var userUpdated = userService.updateUser(userId, updateUserRequest);

        return ResponseEntity.ok(new UpdateUserRequest(userUpdated));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseUserRequest> getUser(@PathVariable("userId") UUID userId) {
        var user = userService.getUserById(userId);

        return ResponseEntity.ok(new ResponseUserRequest(user));
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
