package com.a0720i1.project_be.services;

import org.springframework.stereotype.Service;

@Service
public interface AccountRoleService {
    void createAccountRole(int accountId, int roleId);
}
