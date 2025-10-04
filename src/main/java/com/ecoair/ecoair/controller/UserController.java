package com.ecoair.ecoair.controller;

import com.ecoair.ecoair.dtos.UserRequestDTO;
import com.ecoair.ecoair.dtos.UserResponseDTO;
import com.ecoair.ecoair.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO userCreated = userService.createUser(userRequestDTO);

        return ResponseEntity.status(201).body(userCreated);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> listUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findUsersById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.findUsersById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUserById(@PathVariable Long id, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        return ResponseEntity.ok(userService.updateUserById(id, userRequestDTO));
    }

}
