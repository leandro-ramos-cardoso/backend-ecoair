package com.ecoair.ecoair.dtos;

import java.time.LocalDateTime;

public record DeviceResponseDTO(
        Long id,
        String deviceName,
        String mac,
        Double latitude,
        Double longitude,
        String gasType,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
