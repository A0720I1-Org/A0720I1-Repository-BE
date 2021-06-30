package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.dto.teacher.TeacherUpdateDTO;
import com.a0720i1.project_be.dto.teacher.TeacherViewDTO;
import com.a0720i1.project_be.models.Account;
import com.a0720i1.project_be.repositories.AccountRepository;
import com.a0720i1.project_be.repositories.TeacherRepository;
import com.a0720i1.project_be.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Account findByUsername(String username) {
        return accountRepository.findAccountByUsername(username);
    }

    @Override
    public int findIdByUsername(String username) {
        return accountRepository.findIdUserByUsername(username);
    }

    @Override
    public void changePassword(Account account, String oldPassword, String newPassword, String confirmPassword) {
        if (checkChangePassword(account,oldPassword,newPassword,confirmPassword)) {
            accountRepository.changePassword(passwordEncoder.encode( newPassword ), account.getUsername());
        }
    }

    @Override
    public boolean checkChangePassword(Account account, String oldPassword, String newPassword, String confirmPassword) {
        if (passwordEncoder.matches( oldPassword, account.getPassword())) {
            if (!oldPassword.equals(newPassword) && newPassword.equals(confirmPassword)){
                return true;
            };
        }
        return false;
    }

    @Override
    public TeacherViewDTO getInfoAccount(String username) {
        Account account = accountRepository.findAccountByUsername(username);
        if(account == null) {
            return null;
        }else {
            return accountRepository.getInfoAccount(account.getId());
        }
    }

    @Override
    public void updateInfoAccount(TeacherUpdateDTO teacherUpdateDTO,String username) {
        Account account = accountRepository.findAccountByUsername(username);
        if(account == null) {
            return ;
        }else {
            accountRepository.updadeInfoAccount(teacherUpdateDTO.getAddress(),teacherUpdateDTO.getHometown(),
                    teacherUpdateDTO.getPosition(),teacherUpdateDTO.getLevel(),teacherUpdateDTO.getPhone(),teacherUpdateDTO.getEmail(),teacherUpdateDTO.getImageUrl(),account.getId());
        }
    }

    @Override
    public String getMailByUsername(String username) {
        return accountRepository.getEmail(username);
    }
}
