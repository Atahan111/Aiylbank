package com.example.demo.services;

import com.example.demo.dto.AuthenticationSignInResponse;
import com.example.demo.dto.AuthenticationSignUpResponse;
import com.example.demo.dto.SignInRequest;
import com.example.demo.dto.SignUpRequest;

public interface AuthenticationService {

    AuthenticationSignInResponse signIn(SignInRequest signInRequest);

    AuthenticationSignUpResponse signUp(SignUpRequest signUpRequest);
}
