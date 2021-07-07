package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.dto.class_student.CreateStudentDTO;
import com.a0720i1.project_be.repositories.StudentRepository;
import com.a0720i1.project_be.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void createStudentDTO(CreateStudentDTO createStudentDTO) {
        this.studentRepository.createStudentDTO(createStudentDTO.getName(), createStudentDTO.getBirthday(), createStudentDTO.getGender(),
                createStudentDTO.getHometown(), createStudentDTO.getEmail() , createStudentDTO.getReligion() , createStudentDTO.getEthnicity());
    }

    @Override
    public int getIdByEmail(String email) {
        return studentRepository.getIdByEmail(email);
    }

    @Override
    public void setAccountId(int accountId , int studentId) {
        studentRepository.setAccountId( accountId , studentId);

    }


}
