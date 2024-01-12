package com.example.demo.dto;

import com.example.demo.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class AuthenticationSignInResponse {
    private Long id;
    private String email;
    private String fullName;
    private String token;
    private Role role;
}
