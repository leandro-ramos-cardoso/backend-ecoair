package com.ecoair.ecoair.mapper;

import com.ecoair.ecoair.dtos.UserRequestDTO;
import com.ecoair.ecoair.dtos.UserResponseDTO;
import com.ecoair.ecoair.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserRequestDTO userRequestDTO);
    UserResponseDTO toDTO(User user);
}