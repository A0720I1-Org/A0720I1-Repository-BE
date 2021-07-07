package com.a0720i1.project_be.services;


import com.a0720i1.project_be.dto.class_student.CreateStudentDTO;

public interface StudentService {
    void createStudentDTO(CreateStudentDTO createStudentDTO);
    int getIdByEmail(String email);
    void setAccountId(int accountId , int studentId);
}
