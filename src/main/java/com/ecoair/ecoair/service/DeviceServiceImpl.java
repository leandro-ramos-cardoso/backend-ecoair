package com.ecoair.ecoair.service;

import com.ecoair.ecoair.dtos.DeviceRequestDTO;
import com.ecoair.ecoair.dtos.DeviceResponseDTO;
import com.ecoair.ecoair.mapper.DeviceMapper;
import com.ecoair.ecoair.model.Device;
import com.ecoair.ecoair.repository.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    @Override
    public DeviceResponseDTO createDevice(DeviceRequestDTO dto) {
        String mac = dto.mac().toUpperCase();

        Device existingDevice = deviceRepository.findByMac(mac)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.FORBIDDEN,
                        "O dispositivo com o MAC " + mac + " não está autorizado a enviar dados."
                ));

        existingDevice.setDeviceName(dto.deviceName());
        existingDevice.setLatitude(dto.latitude());
        existingDevice.setLongitude(dto.longitude());
        existingDevice.setGasType(dto.gasType());

        Device saved = deviceRepository.save(existingDevice);
        return deviceMapper.toDTO(saved);
    }


    @Override
    public List<DeviceResponseDTO> listDevices() {
        return deviceRepository.findAll().stream()
                .map(deviceMapper::toDTO)
                .toList();
    }

    @Override
    public DeviceResponseDTO findDeviceById(Long id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dispositivo não encontrado"));
        return deviceMapper.toDTO(device);
    }

    @Override
    public DeviceResponseDTO updateDeviceById(Long id, DeviceRequestDTO dto) {
        Device existing = deviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dispositivo não encontrado"));
        existing.setDeviceName(dto.deviceName());
        existing.setMac(dto.mac());
        existing.setLatitude(dto.latitude());
        existing.setLongitude(dto.longitude());
        existing.setGasType(dto.gasType());
        Device updated = deviceRepository.save(existing);
        return deviceMapper.toDTO(updated);
    }
}
