package com.a0720i1.project_be.dto;

import com.a0720i1.project_be.models.Account;
import lombok.Data;

import java.util.List;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Account account;
    private List<String> roles;

    public JwtResponse(String token, Account account, List<String> roles) {
        this.token = token;
        this.account = account;
        this.roles = roles;
    }
}
