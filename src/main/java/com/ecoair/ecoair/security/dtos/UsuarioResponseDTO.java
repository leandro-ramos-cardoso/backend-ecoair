package com.ecoair.ecoair.security.dtos;

import com.ecoair.ecoair.security.enums.Role;

public record UsuarioResponseDTO(
        String username,
        String email,
        Role role
) {
}
