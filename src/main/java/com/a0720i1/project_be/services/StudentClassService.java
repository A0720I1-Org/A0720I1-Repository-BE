package com.a0720i1.project_be.services;

import com.a0720i1.project_be.dto.class_student.ClassStudentListDTO;
import com.a0720i1.project_be.dto.class_student.ClassStudentNameDTO;
import com.a0720i1.project_be.dto.class_student.ClassStudentTeacherListDTO;
import com.a0720i1.project_be.dto.teacher.TeacherListDTO;
import com.a0720i1.project_be.models.SchoolYear;
import com.a0720i1.project_be.models.StudentClass;
import com.a0720i1.project_be.models.Teacher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentClassService {
    List<ClassStudentTeacherListDTO> getAllTeacher();
    List<ClassStudentNameDTO> getAllClassName();
    List<ClassStudentListDTO> getAllStudent();





}
