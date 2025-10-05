package com.ecoair.ecoair.controller;

import com.ecoair.ecoair.dtos.SensorDataRequestDTO;
import com.ecoair.ecoair.dtos.SensorDataResponseDTO;
import com.ecoair.ecoair.service.SensorDataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sensor-data")
@RequiredArgsConstructor
public class SensorDataController {

    private final SensorDataService sensorDataService;

    @PostMapping
    public ResponseEntity<SensorDataResponseDTO> createSensorData(@Valid @RequestBody SensorDataRequestDTO dto) {
        SensorDataResponseDTO sensorCreate = sensorDataService.createSensorData(dto);
        return ResponseEntity.status(201).body(sensorCreate);
    }

    @PostMapping("/save")
    public ResponseEntity<Void> saveReading(@Valid @RequestBody SensorDataRequestDTO dto) {
        sensorDataService.saveReading(dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<SensorDataResponseDTO>> listSensorData() {
        return ResponseEntity.ok(sensorDataService.listSensorData());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorDataResponseDTO> findSensorDataById(@PathVariable Long id) {
        return ResponseEntity.ok(sensorDataService.findSensorDataById(id));
    }

    @GetMapping("/{mac}")
    public ResponseEntity<List<SensorDataResponseDTO>> findSensorByMac(@PathVariable String mac) {
        return ResponseEntity.ok(sensorDataService.findSensorDataByMac(mac));
    }

    @GetMapping("/{mac}/range")
    public ResponseEntity<List<SensorDataResponseDTO>> getByMacAndTimeRange(
            @PathVariable String mac,
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime
    ) {
        var result = sensorDataService.findSensorDataByMacAndTimeRange(mac, startTime, endTime);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/gas")
    public ResponseEntity<List<SensorDataResponseDTO>> getByGasType(
            @RequestParam String gasType
    ) {
        var result = sensorDataService.findSensorDataByGasType(gasType);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{mac}/latest")
    public ResponseEntity<SensorDataResponseDTO> getLatestByMac(@PathVariable String mac) {
        return sensorDataService.findLatestSensorDataByMac(mac)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{mac}/gas")
    public ResponseEntity<List<SensorDataResponseDTO>> getByMacAndGasType(
            @PathVariable String mac,
            @RequestParam String gasType
    ) {
        var result = sensorDataService.findSensorDataByMacAndGasType(mac, gasType);
        return ResponseEntity.ok(result);
    }
}
