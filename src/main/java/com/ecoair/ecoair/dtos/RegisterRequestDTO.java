package com.ecoair.ecoair.dtos;

import com.ecoair.ecoair.enums.Role;

public record RegisterRequestDTO(
        String username,
        String password,
        String email,
        Role role
) {
}
