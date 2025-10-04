package com.ecoair.ecoair.controller;

import com.ecoair.ecoair.dtos.SensorDataResponseDTO;
import com.ecoair.ecoair.service.SensorDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/sensor-data")
@RequiredArgsConstructor
public class SensorDataController {

    private final SensorDataService sensorDataService;

    @GetMapping("/{mac}")
    public ResponseEntity<List<SensorDataResponseDTO>> findSensorByMac(@PathVariable String mac) {
        return ResponseEntity.ok(sensorDataService.findSensorDataByMac(mac));
    }
}
