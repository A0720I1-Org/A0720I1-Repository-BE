package com.a0720i1.project_be.jwt;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
