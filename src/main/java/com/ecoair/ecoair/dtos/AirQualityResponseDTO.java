package com.ecoair.ecoair.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AirQualityResponseDTO {
    private String deviceStatus;
    private Double sensorValue;
    private String unit;
    private String lastUpdated;
    private Double latitude;
    private Double longitude;
}
