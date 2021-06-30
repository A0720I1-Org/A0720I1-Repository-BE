package com.a0720i1.project_be.services;


import com.a0720i1.project_be.dto.student.StudentDeleteDTO;
import com.a0720i1.project_be.dto.student.StudentListDTO;
import com.a0720i1.project_be.dto.student.StudentUpdateDTO;
import com.a0720i1.project_be.dto.student.StudentViewDTO;

import java.util.List;

public interface StudentService {
    List<StudentListDTO> getPageAllStudent(int index);
    List<StudentListDTO> getAllStudent();
    StudentViewDTO getStudentById(int id);
    void deleteStudent(int id);
    StudentDeleteDTO getStudentFullById(int id);
    void updateStudent(StudentUpdateDTO studentUpdateDTO);
}
