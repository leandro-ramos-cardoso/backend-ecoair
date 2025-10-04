package com.ecoair.ecoair.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DeviceRequestDTO(

        @NotBlank(message = "Nome do dispositivo é obrigatório")
        String deviceName,

        @NotBlank(message = "Endereço MAC é obrigatório")
        @Pattern(
                regexp = "^(?:[0-9A-Fa-f]{2}:){5}[0-9A-Fa-f]{2}$",
                message = "Formato de MAC inválido. Use o padrão XX:XX:XX:XX:XX:XX (ex: 78:21:84:8C:B4:F8)"
        )
        String mac,

        @NotNull(message = "Latitude é obrigatória")
        Double latitude,

        @NotNull(message = "Longitude é obrigatória")
        Double longitude,

        @NotBlank(message = "Tipo de gás é obrigatório")
        String gasType


) {
}
