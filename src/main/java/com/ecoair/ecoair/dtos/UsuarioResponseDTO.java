package com.ecoair.ecoair.dtos;

import com.ecoair.ecoair.enums.Role;

public record UsuarioResponseDTO(
        String username,
        String email,
        Role role
) {
}
