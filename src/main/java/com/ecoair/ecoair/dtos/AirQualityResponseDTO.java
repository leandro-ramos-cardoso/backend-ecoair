package com.ecoair.ecoair.dtos;

import lombok.Data;

@Data
public class AirQualityResponseDTO {
    private String deviceStatus;   // gerado localmente
    private Double sensorValue;    // calculado localmente
    private String unit;           // vem da OpenAQ
    private String lastUpdated;    // vem do banco (createdAt)
    private Double latitude;       // vem da OpenAQ
    private Double longitude;      // vem da OpenAQ
}
