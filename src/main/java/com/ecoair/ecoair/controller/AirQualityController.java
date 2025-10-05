package com.ecoair.ecoair.controller;

import com.ecoair.ecoair.dtos.AirQualityResponseDTO;
import com.ecoair.ecoair.service.AirQualityIntegrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/air-quality")
@RequiredArgsConstructor
public class AirQualityController {

    private final AirQualityIntegrationService service;

    @GetMapping("/{country}")
    public AirQualityResponseDTO getAirQuality(@PathVariable String country) {
        return service.getCombinedData(country);
    }
}
