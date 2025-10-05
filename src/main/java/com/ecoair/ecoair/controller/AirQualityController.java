package com.ecoair.ecoair.controller;

import com.ecoair.ecoair.enums.AirQualityLevel;
import com.ecoair.ecoair.utils.AirQualityEvaluator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/airquality")
public class AirQualityController {

    @GetMapping("/co")
    public String avaliarCO(@RequestParam BigDecimal valor) {
        AirQualityLevel nivel = AirQualityEvaluator.classify(valor);
        return "Qualidade do ar (CO): " + nivel;
    }
}
