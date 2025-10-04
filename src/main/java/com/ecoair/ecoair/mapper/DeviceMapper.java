package com.ecoair.ecoair.mapper;

import com.ecoair.ecoair.dtos.DeviceRequestDTO;
import com.ecoair.ecoair.dtos.DeviceResponseDTO;
import com.ecoair.ecoair.model.Device;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DeviceMapper {
    Device toEntity(DeviceRequestDTO dto);
    DeviceResponseDTO toDTO(Device entity);
}
