package com.ecoair.ecoair.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OpenAQResponseDTO {

    private Meta meta;
    private List<MeasurementDTO> results;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Meta {
        private String name;
        private String license;
        private String website;
        private int page;
        private int limit;
        private int found;
    }
}
