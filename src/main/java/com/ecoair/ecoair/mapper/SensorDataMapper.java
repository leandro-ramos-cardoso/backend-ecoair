package com.ecoair.ecoair.mapper;

import com.ecoair.ecoair.dtos.SensorDataRequestDTO;
import com.ecoair.ecoair.dtos.SensorDataResponseDTO;
import com.ecoair.ecoair.model.SensorData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SensorDataMapper {

    SensorData toEntity(SensorDataRequestDTO sensorDataRequestDTO);
    SensorDataResponseDTO toDTO(SensorData sensorData);
}
