package com.ecoair.ecoair.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.DecimalMax;

import java.time.LocalDateTime;

public record SensorDataRequestDTO(

        @NotBlank(message = "Endereço MAC é obrigatório")
        @Pattern(
                regexp = "^(?:[0-9A-Fa-f]{2}:){5}[0-9A-Fa-f]{2}$",
                message = "Formato de MAC inválido. Use o padrão XX:XX:XX:XX:XX:XX (ex: 78:21:84:8C:B4:F8)"
        )
        String mac,

        @NotNull(message = "Valor do sensor é obrigatório")
        @DecimalMin(value = "0.0", message = "Valor do sensor deve ser maior ou igual a 0")
        @DecimalMax(value = "1000.0", message = "Valor do sensor deve ser menor ou igual a 1000")
        Double sensorValue,

        @NotBlank(message = "Tipo de gás é obrigatório")
        String gasType,

        @NotNull(message = "Latitude é obrigatória")
        @DecimalMin(value = "-90.0", message = "Latitude deve estar entre -90 e 90")
        @DecimalMax(value = "90.0", message = "Latitude deve estar entre -90 e 90")
        Double latitude,

        @NotNull(message = "Longitude é obrigatória")
        @DecimalMin(value = "-180.0", message = "Longitude deve estar entre -180 e 180")
        @DecimalMax(value = "180.0", message = "Longitude deve estar entre -180 e 180")
        Double longitude,

        @NotNull(message = "Timestamp é obrigatório")
        LocalDateTime timestamp

) {
}
