package com.irarrazabal.iglesiaapi.application.service;

import com.irarrazabal.iglesiaapi.application.dto.auth.*;
import com.irarrazabal.iglesiaapi.domain.model.Rol;
import com.irarrazabal.iglesiaapi.domain.model.User;
import com.irarrazabal.iglesiaapi.domain.repository.UserRepository;
import com.irarrazabal.iglesiaapi.exceptions.*;
import com.irarrazabal.iglesiaapi.infraestructure.email.EmailService;
import com.irarrazabal.iglesiaapi.infraestructure.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;

    public AuthService(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,  EmailService emailService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.emailService = emailService;
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
        user.setEnabled(false);

        String token = UUID.randomUUID().toString();

        user.setVerificationToken(token);

        userRepository.save(user);

        String verificationLink = "http://localhost:8081/auth/verify?token=" + token;

        emailService.sendVerificationMail(
                user.getEmail(),
                verificationLink
        );

    }


    public AuthResponse login(LoginRequest request) {

        Authentication authentication;
        try {

            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.email(),
                            request.password()
                    )
            );

        } catch (DisabledException e) {
            throw new InvalidCredentialExceptions(
                    "Debes verificar tu correo antes de iniciar sesión."
            );
        }
        catch (BadCredentialsException | UsernameNotFoundException e) {
            throw new InvalidCredentialExceptions("Email o password incorrectos");

        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = jwtService.generateToken(userDetails);

        return new AuthResponse(token);
    }


    public void changeRole ( Long id, ChangeRoleRequest request){
        User user = userRepository.findById(id)
                .orElseThrow();
        user.setRol(request.rol());
        userRepository.save(user);
    }


    @Transactional(readOnly = true)
    public CurrentUserResponse me(){

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        User user=  userRepository
                .findByEmail(email).orElseThrow();

        return new CurrentUserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRol()
        );
    }

    @Transactional
    public void changePassword(String email, ChangePasswordRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        if(!passwordEncoder.matches(
                request.currentPassword(),user.getPassword()
        )){
            throw new IncorrectPasswordException("La contaseña actual es incorrecta");

        }
        if(passwordEncoder.matches(
                request.newPassword(),user.getPassword()
        )){
            throw  new PasswordMismatchExceptions("La nueva contraseña debe ser distinta a la actual");
        }

        if(!request.newPassword().equals(request.confirmPassword())){
            throw new SamePasswordExceptions("La constraseñas no coinciden");
        }

    user.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);

    }

    @Transactional
    public void forgotPassword(ForgotPasswordRequest request){

        userRepository.findByEmail(request.email())
                .ifPresent(user -> {

                    String token = UUID.randomUUID().toString();

                    user.setResetPasswordToken(token);

                    user.setResetPasswordExpiration(
                            LocalDateTime.now().plusMinutes(30)
                    );

                    userRepository.save(user);

                    String resetLink =
                            "http://localhost:4200/reset-password?token=" + token;

                    emailService.sendResetPasswordEmail(
                            user.getEmail(),
                            resetLink
                    );

                });

    }


    @Transactional
    public void resetPassword(ResetPasswordRequest request){

        User user = userRepository.findByResetPasswordToken(request.token())
                .orElseThrow(() -> new InvalidTokenException("Token inválido"));

        if (user.getResetPasswordExpiration().isBefore(LocalDateTime.now())) {
            throw new InvalidTokenException(
                    "El enlace ha expirado."
            );
        }

        if(!request.newPassword().equals(request.confirmPassword())){
            throw new InvalidTokenException(
                    "Las contraseñas no coinciden."
            );
        }

        user.setPassword(
                passwordEncoder.encode(request.newPassword())
        );

        user.setResetPasswordToken(null);
        user.setResetPasswordExpiration(null);

        userRepository.save(user);



    }





@Transactional(readOnly = true)
    public void verifyAccount( String token){
        User user = userRepository.findByVerificationToken(token)
                .orElseThrow(() -> new  InvalidCredentialExceptions("Token invalido o expirado"));

        user.setEnabled(true);
        user.setVerificationToken(null);
        userRepository.save(user);
    }


    @Transactional
    public void resendVerificationEmail(ResendVerificationRequest request) {

        userRepository.findByEmail(request.email())
                .filter(user -> !user.isEnabled())
                .ifPresent(user -> {

                    String token = UUID.randomUUID().toString();

                    user.setVerificationToken(token);
                    userRepository.save(user);

                    String verificationLink =
                            "http://localhost:8081/auth/verify?token=" + token;

                    emailService.sendVerificationMail(
                            user.getEmail(),
                            verificationLink
                    );
                });
    }
}
