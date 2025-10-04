package com.ecoair.ecoair.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(

        @NotBlank(message = "Nome do cliente é obrigatório")
        String name,

        @NotBlank(message = "Nome do cliente é obrigatório")
        String email,

        @NotBlank(message = "Nome do cliente é obrigatório")
        String password
) {
}
