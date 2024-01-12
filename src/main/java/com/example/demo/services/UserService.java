package com.example.demo.services;

import com.example.demo.dto.AuthenticationSignInResponse;
import com.example.demo.dto.AuthenticationSignUpResponse;
import com.example.demo.dto.SignInRequest;
import com.example.demo.dto.SignUpRequest;

public interface UserService {
    AuthenticationSignUpResponse signUp(SignUpRequest signUpRequest);

    AuthenticationSignInResponse signIn(SignInRequest signInRequest);
}
