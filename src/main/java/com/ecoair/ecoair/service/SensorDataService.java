package com.ecoair.ecoair.service;

import com.ecoair.ecoair.dtos.SensorDataRequestDTO;
import com.ecoair.ecoair.dtos.SensorDataResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface SensorDataService {
    SensorDataResponseDTO createSensorData(SensorDataRequestDTO sensorDataRequestDTO);
    //void saveReading(SensorDataRequestDTO sensorDataRequestDTO);
    List<SensorDataResponseDTO> listSensorData();
    SensorDataResponseDTO findSensorDataById(Long id);
    List<SensorDataResponseDTO> findSensorDataByMac(String mac);
    List<SensorDataResponseDTO> findSensorDataByMacAndTimeRange(String mac, LocalDateTime startTime, LocalDateTime endTime);
    List<SensorDataResponseDTO> findSensorDataByGasType(String gasType);
    SensorDataResponseDTO findLatestSensorDataByMac(String mac);
    List<SensorDataResponseDTO> findSensorDataByMacAndGasType(String mac, String gasType);
}