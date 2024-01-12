package com.example.demo.services.impl;

import com.example.demo.config.JwtService;
import com.example.demo.dto.AuthenticationSignInResponse;
import com.example.demo.dto.AuthenticationSignUpResponse;
import com.example.demo.dto.SignInRequest;
import com.example.demo.dto.SignUpRequest;
import com.example.demo.entity.User;
import com.example.demo.enums.Role;
import com.example.demo.exceptions.AlreadyExistException;
import com.example.demo.exceptions.BadCredentialException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.AuthenticationService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationSignInResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.getUserByEmail(signInRequest.getEmail()).orElseThrow(() ->
                new NotFoundException(String.format("Пользователь с адресом электронной почты: %s не найден!", signInRequest.getEmail())));

        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            log.info("Недействительный пароль");
            throw new BadCredentialException("Недействительный пароль");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getEmail(),
                        signInRequest.getPassword()));
        String token = jwtService.generateToken(user);
        return AuthenticationSignInResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .fullName(user.getFirstName() + " " + user.getLastName())
                .token(token)
                .role(user.getRole())
                .build();
    }

    @Override
    public AuthenticationSignUpResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new AlreadyExistException("Пользователь с адресом электронной почты:"
                    + signUpRequest.getEmail() + " уже существует");
        }
        User user = new User();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        log.info("Пользователь успешно сохранен с идентификатором:" + user.getId());
        String token = jwtService.generateToken(user);
        return new AuthenticationSignUpResponse(
                user.getId(),
                user.getFirstName() + " " + user.getLastName(),
                token,
                user.getEmail(),
                user.getRole()
        );
    }

}
