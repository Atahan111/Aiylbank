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
import com.example.demo.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthenticationSignInResponse signIn(SignInRequest signInRequest) {
        User user = userRepository.getUserByEmail(signInRequest.getEmail()).orElseThrow(() ->
                new NotFoundException("User with email: %s not found".formatted(signInRequest.getEmail())));
        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            log.info("Недействительный пароль");
            throw new BadCredentialException( "Недействительный пароль");
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getEmail(),
                        signInRequest.getPassword()));
        String token = jwtService.generateToken(user);
        return new AuthenticationSignInResponse(
                user.getId(),
                user.getFirstName()+" " + user.getLastName(),
                token,
                user.getEmail(),
                user.getRole()
        );
    }

    @Override
    public AuthenticationSignUpResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())){
            throw new AlreadyExistException("User with email: %s is exist");
        }
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole(Role.ADMIN);
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthenticationSignUpResponse(
                user.getId(),
                user.getFirstName()+" " + user.getLastName(),
                token,
                user.getEmail(),
                user.getRole()
        );
    }
}
