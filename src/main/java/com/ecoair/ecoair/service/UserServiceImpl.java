package com.ecoair.ecoair.service;

import com.ecoair.ecoair.dtos.UserRequestDTO;
import com.ecoair.ecoair.dtos.UserResponseDTO;
import com.ecoair.ecoair.mapper.UserMapper;
import com.ecoair.ecoair.model.User;
import com.ecoair.ecoair.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        User user = userMapper.toEntity(userRequestDTO);
        return userMapper.toDTO(userRepository.save(user));
    }

    @Override
    public List<UserResponseDTO> listUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::toDTO).toList();
    }

    @Override
    public UserResponseDTO findUsersById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND
                ));
        return userMapper.toDTO(user);
    }

    @Override
    public UserResponseDTO updateUserById(Long id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Cliente não encontrado"));
        user.setName(userRequestDTO.name());

        return userMapper.toDTO(userRepository.save(user));
    }
}