package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.repositories.AccountRoleRepository;
import com.a0720i1.project_be.services.AccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountRoleServiceImpl implements AccountRoleService {
    @Autowired
    AccountRoleRepository accountRoleRepository;

    @Override
    public void createAccountRole(int accountId, int roleId) {
        accountRoleRepository.createAccountRole(accountId, roleId);
    }
}
