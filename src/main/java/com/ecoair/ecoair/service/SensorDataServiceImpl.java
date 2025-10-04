package com.ecoair.ecoair.service;

import com.ecoair.ecoair.dtos.SensorDataRequestDTO;
import com.ecoair.ecoair.dtos.SensorDataResponseDTO;
import com.ecoair.ecoair.mapper.SensorDataMapper;
import com.ecoair.ecoair.model.SensorData;
import com.ecoair.ecoair.repository.SensorDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SensorDataServiceImpl implements SensorDataService {

    private final SensorDataRepository sensorDataRepository;
    private final SensorDataMapper sensorDataMapper;

    @Override
    public SensorDataResponseDTO createSensorData(SensorDataRequestDTO dto) {
        SensorData sensorData = sensorDataMapper.toEntity(dto);
        SensorData saved = sensorDataRepository.save(sensorData);
        return sensorDataMapper.toDTO(saved);
    }

    @Override
    public void saveReading(SensorDataRequestDTO dto) {
        SensorData sensorData = sensorDataMapper.toEntity(dto);
        sensorDataRepository.save(sensorData);
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
    public List<SensorDataResponseDTO> findLatestSensorDataByMac(String mac) {
        return sensorDataRepository.findLatestByMac(mac).stream()
                .map(sensorDataMapper::toDTO)
                .toList();
    }

    @Override
    public List<SensorDataResponseDTO> findSensorDataByMacAndGasType(String mac, String gasType) {
        return sensorDataRepository.findByMacAndGasType(mac, gasType).stream()
                .map(sensorDataMapper::toDTO)
                .toList();
    }
}
