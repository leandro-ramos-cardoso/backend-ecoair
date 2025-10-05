package com.ecoair.ecoair.utils;

import com.ecoair.ecoair.enums.AirQualityLevel;

import java.math.BigDecimal;

public class AirQualityEvaluator {

    private static final BigDecimal GOOD_LIMIT = new BigDecimal("4.4");
    private static final BigDecimal MODERATE_LIMIT = new BigDecimal("9");

    public static AirQualityLevel classify(BigDecimal coValue) {
        if (coValue.compareTo(GOOD_LIMIT) <= 0) {
            return AirQualityLevel.BOA;
        } else if (coValue.compareTo(MODERATE_LIMIT) <= 0) {
            return AirQualityLevel.MEDIA;
        } else {
            return AirQualityLevel.RUIM;
        }
    }
}
