package com.ecoair.ecoair.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MeasurementDTO {
    private String parameter;
    private BigDecimal value;
    private String unit;
    private LocalDateTime dateUtc;
    private String location;
    private Double latitude;
    private Double longitude;
}
