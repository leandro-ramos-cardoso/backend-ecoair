package com.ecoair.ecoair.controller;

import com.ecoair.ecoair.dtos.OpenAQResponseDTO;
import com.ecoair.ecoair.service.OpenAqService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/openaq")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OpenAqController {

    private final OpenAqService openAQService;

    @GetMapping("/latest/{country}")
    public OpenAQResponseDTO getLatest(@PathVariable String country) {
        return openAQService.getLatestByCountry(country);
    }
}