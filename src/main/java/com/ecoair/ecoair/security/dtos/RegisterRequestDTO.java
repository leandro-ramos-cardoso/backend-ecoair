package com.ecoair.ecoair.security.dtos;

import com.ecoair.ecoair.security.enums.Role;

public record RegisterRequestDTO(
        String username,
        String password,
        String email,
        Role role
) {
}
