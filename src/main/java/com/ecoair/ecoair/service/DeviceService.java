package com.ecoair.ecoair.service;

import com.ecoair.ecoair.dtos.DeviceRequestDTO;
import com.ecoair.ecoair.dtos.DeviceResponseDTO;

import java.util.List;

public interface DeviceService {
    DeviceResponseDTO createDevice(DeviceRequestDTO deviceRequestDTO);
    List<DeviceResponseDTO> listDevices();
    DeviceResponseDTO findDeviceById(Long id);
    DeviceResponseDTO updateDeviceById(Long id, DeviceRequestDTO deviceRequestDTO);
}
