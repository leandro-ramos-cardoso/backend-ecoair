package com.ecoair.ecoair.service;

import com.ecoair.ecoair.dtos.MeasurementDTO;
import com.ecoair.ecoair.dtos.OpenAQResponseDTO;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenAQService {

    private final WebClient openAQClient;

    public OpenAQResponseDTO getLatestByCountry(String countryCode) {
        JsonNode response = openAQClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/latest")
                        .queryParam("country", countryCode)
                        .queryParam("limit", 10)
                        .build())
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        List<MeasurementDTO> measurements = new ArrayList<>();

        if (response != null && response.has("results")) {
            for (JsonNode locationNode : response.get("results")) {
                String location = locationNode.get("location").asText();
                JsonNode coordinates = locationNode.path("coordinates");

                for (JsonNode measurementNode : locationNode.path("measurements")) {
                    MeasurementDTO dto = new MeasurementDTO();
                    dto.setParameter(measurementNode.path("parameter").asText());
                    dto.setValue(measurementNode.path("value").asDouble());
                    dto.setUnit(measurementNode.path("unit").asText());
                    dto.setLastUpdated(measurementNode.path("lastUpdated").asText());
                    dto.setLocation(location);
                    dto.setLatitude(coordinates.path("latitude").asDouble());
                    dto.setLongitude(coordinates.path("longitude").asDouble());
                    measurements.add(dto);
                }
            }
        }

        OpenAQResponseDTO dto = new OpenAQResponseDTO();
        dto.setCountry(countryCode.toUpperCase());
        dto.setMeasurements(measurements);

        return dto;
    }
}
