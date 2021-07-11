package com.a0720i1.project_be.controllers;

import com.a0720i1.project_be.dto.class_student.CreateStudentDTO;
import com.a0720i1.project_be.models.AccountRole;
import com.a0720i1.project_be.services.AccountRoleService;
import com.a0720i1.project_be.services.AccountService;
import com.a0720i1.project_be.services.StudentService;
import com.a0720i1.project_be.services.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRoleService accountRoleService;
    @Autowired
    private BCryptPasswordEncoder encoder;


    @PostMapping("/api/admin/student/create-student")
    @Transactional
    public ResponseEntity<?> createTeacher(@RequestBody CreateStudentDTO createStudentDTO) {
        String defaultPassword = "123456";
        this.studentService.createStudentDTO(createStudentDTO);
        int studentId = studentService.getIdByEmail(createStudentDTO.getEmail());
        String userName = String.format("%05d", studentId);
        accountService.createAccount(userName, encoder.encode(defaultPassword), 1);
        int accountId = accountService.findIdByUsername(userName);
        accountRoleService.createAccountRole(accountId, 3);
        studentService.setAccountId(accountId , studentId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
