package com.a0720i1.project_be.services;

import com.a0720i1.project_be.models.AccountRole;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountRoleService {
    void createAccountRole(int accountId, int roleId);
    List<AccountRole> findAllByAccount_Username(String username);
}
