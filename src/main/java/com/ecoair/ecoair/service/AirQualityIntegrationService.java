package com.ecoair.ecoair.service;

import com.ecoair.ecoair.dtos.AirQualityResponseDTO;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AirQualityIntegrationService {

    private final WebClient openAQClient;

    public AirQualityResponseDTO getCombinedData(String country) {

        BigDecimal localSensorValue = new BigDecimal("6.2"); // ppm CO
        String localStatus = localSensorValue.doubleValue() > 5.0 ? "ALERTA" : "NORMAL";

        JsonNode response = openAQClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v3beta/measurements")
                        .queryParam("country", country)
                        .queryParam("limit", 1)
                        .build())
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        JsonNode result = response.path("results").get(0);
        JsonNode coords = result.path("coordinates");

        String dataOpenAQ = result.path("date").path("utc").asText();
        if (dataOpenAQ == null || dataOpenAQ.isBlank()) {
            dataOpenAQ = LocalDateTime.now().toString();
        }

        AirQualityResponseDTO dto = new AirQualityResponseDTO();
        dto.setDeviceStatus(localStatus);
        dto.setSensorValue(localSensorValue.doubleValue());
        dto.setUnit(result.path("unit").asText());
        dto.setLastUpdated(dataOpenAQ);
        dto.setLatitude(coords.path("latitude").asDouble());
        dto.setLongitude(coords.path("longitude").asDouble());

        return dto;
    }
}