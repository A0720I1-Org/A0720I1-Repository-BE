package com.a0720i1.project_be.services;


import com.a0720i1.project_be.dto.student.StudentDeleteDTO;
import com.a0720i1.project_be.dto.student.StudentListDTO;
import com.a0720i1.project_be.dto.student.StudentUpdateDTO;
import com.a0720i1.project_be.dto.student.StudentViewDTO;

import java.util.List;

public interface StudentService {

    List<StudentListDTO> getAllStudent(int classId);

    List<StudentListDTO> searchStudentByNameAndHometown(int index, String name, String hometown);

    StudentViewDTO getStudentById(int id);

    void deleteStudent(int id);

    StudentDeleteDTO getStudentFullById(int id);

    void updateStudent(StudentUpdateDTO studentUpdateDTO);

    List<StudentListDTO> getAllStudentByClassId(int classId, int index);
}
