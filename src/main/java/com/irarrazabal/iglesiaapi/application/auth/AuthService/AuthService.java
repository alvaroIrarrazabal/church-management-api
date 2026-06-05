package com.irarrazabal.iglesiaapi.application.auth.AuthService;

import com.irarrazabal.iglesiaapi.application.auth.dto.AuthResponse;
import com.irarrazabal.iglesiaapi.application.auth.dto.LoginRequest;
import com.irarrazabal.iglesiaapi.application.auth.dto.RegisterRequest;
import com.irarrazabal.iglesiaapi.domain.model.User;
import com.irarrazabal.iglesiaapi.domain.repository.UserRepository;
import com.irarrazabal.iglesiaapi.infraestructure.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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


    public void register(RegisterRequest request) {

        String passwordHash =
                passwordEncoder.encode(
                        request.password()
                );
        User user = new User(
                null,
                request.username(),
                passwordHash,
                request.rol()
        );
        userRepository.save(user);
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
