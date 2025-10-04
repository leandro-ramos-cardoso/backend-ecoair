package com.ecoair.ecoair.security.dtos;

public record LoginRequestDTO(
        String username,
        String password
) {
}