package com.hermes.user_service.user;

import com.hermes.user_service.user.dto.request.CreateUserRequest;
import com.hermes.user_service.user.dto.response.UserResponse;
import com.hermes.user_service.user.dto.request.UpdateUserRequest;
import com.hermes.user_service.user.enums.Role;
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

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/costumer")
    @Transactional
    public ResponseEntity<Void> createCostumer(@RequestBody @Valid CreateUserRequest request) {
        var userId = service.createUser(request, Role.CUSTOMER);

        return ResponseEntity.created(URI.create("/api/v1/users/" + userId)).build();
    }

    @PostMapping("/technician")
    @Transactional
    public ResponseEntity<Void> createTechnician(@RequestBody @Valid CreateUserRequest request) {
        service.createUser(request, Role.TECHNICIAN);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{user-id}")
    @Transactional
    public ResponseEntity<UpdateUserRequest> updateUser(@PathVariable("user-id") UUID userId,
                                                        @RequestBody @Valid UpdateUserRequest updateUserRequest)
    {
        var userUpdated = service.updateUser(userId, updateUserRequest);

        return ResponseEntity.ok(new UpdateUserRequest(userUpdated));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll() {
        return ResponseEntity.ok(service.findAllUsers());
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("user-id") UUID userId) {
        return ResponseEntity.ok(service.getUserById(userId));
    }

    @DeleteMapping("/{user-id}")
    @Transactional
    public ResponseEntity<Void> deleteUser(@PathVariable("user-id") UUID userId) {
        service.deleteUser(userId);

        return ResponseEntity.noContent().build();
    }
}
