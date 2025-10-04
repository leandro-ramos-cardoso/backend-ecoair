package com.ecoair.ecoair.dtos;

import java.time.LocalDateTime;

public record SensorDataResponseDTO(
        Long id,
        String mac,
        Double sensorValue,
        String gasType
) {
}
