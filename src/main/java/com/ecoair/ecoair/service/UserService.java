package com.ecoair.ecoair.service;

import com.ecoair.ecoair.dtos.UserRequestDTO;
import com.ecoair.ecoair.dtos.UserResponseDTO;

import java.util.List;

public interface UserService {
    UserResponseDTO createUser(UserRequestDTO userRequestDTO);
    List<UserResponseDTO> listUsers();
    UserResponseDTO findUsersById(Long id);
    UserResponseDTO updateUserById(Long id, UserRequestDTO userRequestDTO);
}