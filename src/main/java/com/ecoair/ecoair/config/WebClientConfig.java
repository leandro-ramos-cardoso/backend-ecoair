package com.ecoair.ecoair.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    // 👇 Lê a chave do arquivo application.properties
    @Value("${openaq.api.key}")
    private String apiKey;

    @Bean
    public WebClient openAQClient() {
        return WebClient.builder()
                .baseUrl("https://api.openaq.org/v3") // ✅ nova versão da API
                .defaultHeader("X-API-Key", apiKey)   // ✅ adiciona o header automaticamente
                .build();
    }
}
