package com.ecoair.ecoair.security.service;


import com.ecoair.ecoair.security.dtos.LoginRequestDTO;
import com.ecoair.ecoair.security.dtos.LoginResponseDTO;
import com.ecoair.ecoair.security.dtos.RegisterRequestDTO;
import com.ecoair.ecoair.security.dtos.UsuarioResponseDTO;
import com.ecoair.ecoair.security.model.Usuario;
import com.ecoair.ecoair.security.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Transactional
    public LoginResponseDTO register(RegisterRequestDTO request) {
        // Verificar se o usuário já existe
        if (usuarioRepository.findByUsername(request.username()).isPresent()) {
            log.warn("Tentativa de registro com username já existente: {}", request.username());
            throw new IllegalArgumentException("Username já está em uso");
        }
        
        // Verificar se o email já existe
        if (usuarioRepository.findByEmail(request.email()).isPresent()) {
            log.warn("Tentativa de registro com email já existente: {}", request.email());
            throw new IllegalArgumentException("Email já está em uso");
        }

        Usuario usuario = Usuario.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .role(request.role())
                .build();
        
        usuarioRepository.save(usuario);
        log.info("Novo usuário registrado: {} ({})", usuario.getUsername(), usuario.getRole());

        UserDetails userDetails = User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRole().name())
                .build();

        String token = jwtService.generateToken(userDetails);
        return new LoginResponseDTO(token);
    }

    public LoginResponseDTO login(LoginRequestDTO request) {
        log.debug("Tentando login para usuário: {}", request.username());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.username(), request.password())
            );
        } catch (BadCredentialsException e) {
            log.warn("Falha na autenticação para usuário: {}", request.username());
            throw new BadCredentialsException("Credenciais inválidas");
        }

        Usuario usuario = usuarioRepository.findByUsername(request.username())
                .orElseThrow(() -> {
                    log.error("Usuário não encontrado após autenticação bem-sucedida: {}", request.username());
                    return new UsernameNotFoundException("Usuário não encontrado");
                });

        log.info("Login bem-sucedido para usuário: {}", usuario.getUsername());

        UserDetails userDetails = User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .roles(usuario.getRole().name())
                .build();

        String token = jwtService.generateToken(userDetails);
        return new LoginResponseDTO(token);
    }

    public UsuarioResponseDTO getUsuarioLogado(String username) {
        log.debug("Consultando dados do usuário logado: {}", username);
        
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.warn("Tentativa de consultar usuário inexistente: {}", username);
                    return new UsernameNotFoundException("Usuário não encontrado");
                });

        return new UsuarioResponseDTO(
                usuario.getUsername(),
                usuario.getEmail(),
                usuario.getRole()
        );
    }
}
