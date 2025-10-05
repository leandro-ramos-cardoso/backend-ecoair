package com.ecoair.ecoair.service;

import com.ecoair.ecoair.dtos.SensorDataRequestDTO;
import com.ecoair.ecoair.dtos.SensorDataResponseDTO;
import com.ecoair.ecoair.enums.AirQualityLevel;
import com.ecoair.ecoair.mapper.SensorDataMapper;
import com.ecoair.ecoair.model.SensorData;
import com.ecoair.ecoair.repository.DeviceRepository;
import com.ecoair.ecoair.repository.SensorDataRepository;
import com.ecoair.ecoair.utils.AirQualityEvaluator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorDataServiceImpl implements SensorDataService {

    private final SensorDataRepository sensorDataRepository;
    private final SensorDataMapper sensorDataMapper;

    @Autowired
    private final DeviceRepository deviceRepository;

    @Override
    public SensorDataResponseDTO createSensorData(SensorDataRequestDTO dto) {
        SensorData sensorData = sensorDataMapper.toEntity(dto);
        SensorData saved = sensorDataRepository.save(sensorData);

        if ("CO".equalsIgnoreCase(dto.gasType())) {
            BigDecimal coValue = BigDecimal.valueOf(dto.sensorValue());
            AirQualityLevel nivel = AirQualityEvaluator.classify(coValue);

            deviceRepository.findByMac(dto.mac()).ifPresent(device -> {
                device.setDeviceStatus(nivel.name()); // "BOA" | "MEDIA" | "RUIM"
                deviceRepository.save(device);
            });
        }

        return sensorDataMapper.toDTO(saved);
    }

    @Override
    public List<SensorDataResponseDTO> listSensorData() {
        return sensorDataRepository.findAll().stream()
                .map(sensorDataMapper::toDTO)
                .toList();
    }

    @Override
    public SensorDataResponseDTO findSensorDataById(Long id) {
        SensorData sensorData = sensorDataRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dados do sensor não encontrados"));
        return sensorDataMapper.toDTO(sensorData);
    }

    @Override
    public List<SensorDataResponseDTO> findSensorDataByMac(String mac) {
        return sensorDataRepository.findByMac(mac).stream()
                .map(sensorDataMapper::toDTO)
                .toList();
    }

    @Override
    public List<SensorDataResponseDTO> findSensorDataByMacAndTimeRange(String mac, LocalDateTime startTime, LocalDateTime endTime) {
        return sensorDataRepository.findByMacAndTimestampBetween(mac, startTime, endTime).stream()
                .map(sensorDataMapper::toDTO)
                .toList();
    }

    @Override
    public List<SensorDataResponseDTO> findSensorDataByGasType(String gasType) {
        return sensorDataRepository.findByGasType(gasType).stream()
                .map(sensorDataMapper::toDTO)
                .toList();
    }

    @Override
    public SensorDataResponseDTO findLatestSensorDataByMac(String mac) {
        var data = sensorDataRepository.findTopByMacOrderByIdDesc(mac)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Nenhum dado encontrado para o dispositivo: " + mac
                ));

        return new SensorDataResponseDTO(
                data.getId(),
                data.getMac(),
                data.getSensorValue(),
                data.getGasType(),
                data.getTimestamp(), // usado como createdAt
                data.getTimestamp()  // usado como updatedAt
        );
    }






    @Override
    public List<SensorDataResponseDTO> findSensorDataByMacAndGasType(String mac, String gasType) {
        return sensorDataRepository.findByMacAndGasType(mac, gasType).stream()
                .map(sensorDataMapper::toDTO)
                .toList();
    }
}
