package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.dto.class_student.ClassStudentListDTO;
import com.a0720i1.project_be.dto.class_student.ClassStudentNameDTO;
import com.a0720i1.project_be.dto.class_student.ClassStudentTeacherListDTO;
import com.a0720i1.project_be.dto.teacher.TeacherListDTO;
import com.a0720i1.project_be.models.SchoolYear;
import com.a0720i1.project_be.models.StudentClass;
import com.a0720i1.project_be.repositories.StudentClassRepository;
import com.a0720i1.project_be.services.StudentClassService;
import com.a0720i1.project_be.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentClassServiceImpl implements StudentClassService {

    @Autowired
    private StudentClassRepository studentClassRepository;

    @Override
    public List<ClassStudentTeacherListDTO> getAllTeacher() {
        return studentClassRepository.getAllTeacher();
    }

    @Override
    public List<ClassStudentNameDTO> getAllClassName() {
        return studentClassRepository.getAllClassName();
    }

    @Override
    public List<ClassStudentListDTO> getAllStudent() {
        return studentClassRepository.getAllStudent();
    }




}
