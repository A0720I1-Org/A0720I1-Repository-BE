package com.a0720i1.project_be.services;


import com.a0720i1.project_be.dto.HomeRoomClassDTO;
import com.a0720i1.project_be.dto.StudentHomeroomClassDTO;
import com.a0720i1.project_be.dto.student_class.ClassCreateStudentDTO;
import com.a0720i1.project_be.models.Student;

import java.time.LocalDate;
import java.util.List;

public interface StudentService {
    List<HomeRoomClassDTO> getStudentByTeacherUsername(String username);
    List<HomeRoomClassDTO> getPageStudentByTeacherUsername(int index, String username);
    StudentHomeroomClassDTO getInforStudent(int id);
    List<HomeRoomClassDTO> searchStudentByName(String name, int index);
    List<ClassCreateStudentDTO> getAllStudentByClassId(int classId);
    void createStudent(String name, LocalDate birthday, String gender, String hometown , String email, String religion ,
                       String ethnicity, String imageUrl);
    Integer getIdByEmail(String email);
    void setAccountId(int accountId , int studentId);
}
