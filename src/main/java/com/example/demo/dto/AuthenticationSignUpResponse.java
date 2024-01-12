package com.example.demo.dto;

import com.example.demo.enums.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationSignUpResponse {
    private Long id;
    private String fullName;
    private String token;
    private String email;
    private Role role;

    public AuthenticationSignUpResponse(Long id, String fullName, String token, String email, Role role) {
        this.id = id;
        this.fullName = fullName;
        this.token = token;
        this.email = email;
        this.role = role;
    }
}
