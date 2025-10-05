package com.ecoair.ecoair.dtos;

import lombok.Data;

import java.util.List;

@Data
public class OpenAQResponseDTO {
    private String country;
    private List<MeasurementDTO> measurements;
}
