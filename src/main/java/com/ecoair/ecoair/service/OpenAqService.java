package com.ecoair.ecoair.service;

import com.ecoair.ecoair.dtos.MeasurementDTO;
import com.ecoair.ecoair.dtos.OpenAQResponseDTO;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenAqService {

    private final WebClient openAQClient;

    public OpenAQResponseDTO getLatestByCountry(String countryCode) {
        JsonNode response = openAQClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v3/latest")
                        .queryParam("country", countryCode)
                        .queryParam("limit", 10)
                        .build())
                .retrieve()
                .bodyToMono(JsonNode.class)
                .block();

        List<MeasurementDTO> measurements = new ArrayList<>();

        if (response != null && response.has("results")) {
            for (JsonNode locationNode : response.get("results")) {
                String location = locationNode.path("location").asText(null);
                JsonNode coordinates = locationNode.path("coordinates");

                for (JsonNode measurementNode : locationNode.path("measurements")) {
                    String utcString = measurementNode.path("lastUpdated").asText(null);
                    LocalDateTime dateUtc = null;

                    if (utcString != null && !utcString.isEmpty()) {
                        // Remove o 'Z' e faz parsing seguro
                        utcString = utcString.replace("Z", "");
                        try {
                            dateUtc = LocalDateTime.parse(utcString, DateTimeFormatter.ISO_DATE_TIME);
                        } catch (Exception e) {
                            // fallback simples
                            dateUtc = LocalDateTime.now();
                        }
                    }

                    MeasurementDTO dto = MeasurementDTO.builder()
                            .parameter(measurementNode.path("parameter").asText())
                            .value(BigDecimal.valueOf(measurementNode.path("value").asDouble()))
                            .unit(measurementNode.path("unit").asText())
                            .dateUtc(dateUtc)
                            .location(location)
                            .latitude(coordinates.path("latitude").asDouble())
                            .longitude(coordinates.path("longitude").asDouble())
                            .build();

                    measurements.add(dto);
                }
            }
        }

        // Constrói a resposta principal com meta mínima
        OpenAQResponseDTO.Meta meta = OpenAQResponseDTO.Meta.builder()
                .name("OpenAQ")
                .license("CC BY 4.0")
                .website("https://api.openaq.org")
                .page(1)
                .limit(measurements.size())
                .found(measurements.size())
                .build();

        return OpenAQResponseDTO.builder()
                .meta(meta)
                .results(measurements)
                .build();
    }
}
