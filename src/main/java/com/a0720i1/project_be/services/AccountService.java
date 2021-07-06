package com.a0720i1.project_be.services;

import com.a0720i1.project_be.dto.teacher.TeacherUpdateDTO;
import com.a0720i1.project_be.dto.teacher.TeacherViewDTO;
import com.a0720i1.project_be.models.Account;
import org.springframework.stereotype.Service;


@Service
public interface AccountService {
    Account findByUsername(String username);
    int findIdByUsername(String username);
    void createAccount(String username, String password, int isEnable);
    void changePassword(Account account, String oldPassword, String newPassword, String confirmPassword);
    boolean checkChangePassword(Account account, String oldPassword, String newPassword, String confirmPassword);
    TeacherViewDTO getInfoAccount(String username);
    void updateInfoAccount(TeacherUpdateDTO teacherUpdateDTO, String username);
}
