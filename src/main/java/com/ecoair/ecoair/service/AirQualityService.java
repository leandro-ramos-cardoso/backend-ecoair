package com.ecoair.ecoair.service;

import com.ecoair.ecoair.enums.AirQualityLevel;
import com.ecoair.ecoair.utils.AirQualityEvaluator;

import java.math.BigDecimal;

public class AirQualityService {

    public static void main(String[] args) {
        BigDecimal valorCO = new BigDecimal("6.2");

        AirQualityLevel nivel = AirQualityEvaluator.classify(valorCO);

        System.out.println("CO = " + valorCO + " ppm → Qualidade do ar: " + nivel);
    }
}
