package com.ecoair.ecoair.dtos;

import lombok.Data;

@Data
public class MeasurementDTO {
    private String parameter;  // ex: PM2.5, CO, etc.
    private Double value;
    private String unit;
    private String lastUpdated;
    private String location;
    private Double latitude;
    private Double longitude;
}
