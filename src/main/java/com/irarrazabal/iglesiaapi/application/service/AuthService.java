package com.irarrazabal.iglesiaapi.application.service;

import com.irarrazabal.iglesiaapi.application.dto.auth.AuthResponse;
import com.irarrazabal.iglesiaapi.application.dto.auth.LoginRequest;
import com.irarrazabal.iglesiaapi.application.dto.auth.RegisterRequest;
import com.irarrazabal.iglesiaapi.domain.model.Usuario;
import com.irarrazabal.iglesiaapi.domain.repository.UsuarioRepository;
import com.irarrazabal.iglesiaapi.infraestructure.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UsuarioRepository usuarioRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }


    public void register(RegisterRequest request) {

        String passwordHash =
                passwordEncoder.encode(
                        request.password()
                );
        Usuario usuario = new Usuario(
                null,
                request.username(),
                passwordHash,
                request.rol()
        );
        usuarioRepository.save(usuario);
    }


    public AuthResponse login(LoginRequest request) {

        Authentication authentication =
     authenticationManager.authenticate(
             new UsernamePasswordAuthenticationToken(
                     request.username(),
                     request.password()

             )

     );

        String token =
                jwtService.generateToken(
                        request.username()
                );

        return new AuthResponse(token);

    }



}
