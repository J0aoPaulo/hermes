package com.hermes.user_service.user;

import com.hermes.user_service.user.dto.CreateUserRequest;
import com.hermes.user_service.user.dto.UserResponse;
import com.hermes.user_service.user.dto.UpdateUserRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService service;
    private final UserRepository repository;

    public UserController(UserService service, UserRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> createUser(@RequestBody @Valid CreateUserRequest request) {
        var userId = service.createUser(request);

        return ResponseEntity.created(URI.create("/api/v1/users/" + userId)).build();
    }

    @PutMapping("/{userId}")
    @Transactional
    public ResponseEntity<UpdateUserRequest> updateUser(@PathVariable("userId") UUID userId,
                                                        @RequestBody @Valid UpdateUserRequest updateUserRequest)
    {
        var userUpdated = service.updateUser(userId, updateUserRequest);

        return ResponseEntity.ok(new UpdateUserRequest(userUpdated));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(service.findAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("userId") UUID userId) {
        var user = service.getUserById(userId);

        return ResponseEntity.ok(new UserResponse(user));
    }

    @DeleteMapping("/{userId}")
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") UUID userId) {
        if(repository.existsById(userId)) {
            repository.deleteById(userId);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
