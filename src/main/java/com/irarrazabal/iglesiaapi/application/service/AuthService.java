package com.irarrazabal.iglesiaapi.application.service;

import com.irarrazabal.iglesiaapi.application.dto.auth.AuthResponse;
import com.irarrazabal.iglesiaapi.application.dto.auth.ChangeRoleRequest;
import com.irarrazabal.iglesiaapi.application.dto.auth.LoginRequest;
import com.irarrazabal.iglesiaapi.application.dto.auth.RegisterRequest;
import com.irarrazabal.iglesiaapi.domain.model.Rol;
import com.irarrazabal.iglesiaapi.domain.model.User;
import com.irarrazabal.iglesiaapi.domain.repository.UserRepository;
import com.irarrazabal.iglesiaapi.exceptions.UserAlreadyExistsException;
import com.irarrazabal.iglesiaapi.infraestructure.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

@Transactional
    public void register(RegisterRequest request) {
        if(userRepository.existsByEmail(request.email())){
            throw new UserAlreadyExistsException("El email ya está registrado");
        }
        if(userRepository.existsByUsername(request.username())){
            throw new UserAlreadyExistsException("El nombre de usuario ya existe");
        }

        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRol(Rol.INTEGRANTE);

        userRepository.save(user);

    }


    public AuthResponse login(LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.email(),
                                request.password()
                        )
                );

        User user = userRepository.findByEmail(request.email())
                .orElseThrow();

        String token = jwtService.generateToken(user);

        return new AuthResponse(token);
    }


    public void changeRole ( Long id, ChangeRoleRequest request){
        User user = userRepository.findById(id)
                .orElseThrow();
        user.setRol(request.rol());
        userRepository.save(user);
    }



}
