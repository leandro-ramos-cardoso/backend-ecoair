package com.ecoair.ecoair.controller;

import com.ecoair.ecoair.dtos.DeviceRequestDTO;
import com.ecoair.ecoair.dtos.DeviceResponseDTO;
import com.ecoair.ecoair.dtos.SensorDataRequestDTO;
import com.ecoair.ecoair.mapper.DeviceMapper;
import com.ecoair.ecoair.service.DeviceService;
import com.ecoair.ecoair.service.SensorDataService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/device")
@RequiredArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;
    private final SensorDataService sensorDataService;

    @PostMapping
    public ResponseEntity<DeviceResponseDTO> createDevice(@Valid @RequestBody DeviceRequestDTO deviceRequestDTO) {
        DeviceResponseDTO deviceCreated = deviceService.createDevice(deviceRequestDTO);

        return ResponseEntity.status(201).body(deviceCreated);
    }

    @GetMapping
    public ResponseEntity<List<DeviceResponseDTO>> listDevices() {
        return ResponseEntity.ok(deviceService.listDevices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeviceResponseDTO> findDeviceById(@PathVariable Long id) {
        return ResponseEntity.ok(deviceService.findDeviceById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeviceResponseDTO> updateDeviceById(@PathVariable Long id, @Valid @RequestBody DeviceRequestDTO deviceRequestDTO) {
        return ResponseEntity.ok(deviceService.updateDeviceById(id, deviceRequestDTO));
    }

    @PostMapping("/data")
    public ResponseEntity<String> receiveSensorData(@Valid @RequestBody SensorDataRequestDTO dto) {
        sensorDataService.saveReading(dto);
        return ResponseEntity.ok("Dados recebidos com sucesso");
    }

}
