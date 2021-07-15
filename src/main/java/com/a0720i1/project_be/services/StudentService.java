package com.a0720i1.project_be.services;

import com.a0720i1.project_be.dto.class_student.CreateStudentDTO;
import com.a0720i1.project_be.dto.HomeRoomClassDTO;
import com.a0720i1.project_be.dto.StudentHomeroomClassDTO;
import com.a0720i1.project_be.dto.student_class.ClassCreateStudentDTO;

import java.time.LocalDate;
import java.util.List;
import com.a0720i1.project_be.dto.student.StudentDeleteDTO;
import com.a0720i1.project_be.dto.student.StudentListDTO;
import com.a0720i1.project_be.dto.student.StudentUpdateDTO;
import com.a0720i1.project_be.dto.student.StudentViewDTO;

public interface StudentService {

    List<HomeRoomClassDTO> getStudentByTeacherUsername(String username);

    List<HomeRoomClassDTO> getPageStudentByTeacherUsername(int index, String username);

    StudentHomeroomClassDTO getInforStudent(int id);

    List<HomeRoomClassDTO> searchStudentByName(String name, int index);

    List<ClassCreateStudentDTO> getAllStudentByClassId(int classId);
    void createStudent(String name, LocalDate birthday, String gender, String hometown , String email, String religion ,
                       String ethnicity, String imageUrl);
    Integer getIdByEmail(String email);

    void createStudentDTO(CreateStudentDTO createStudentDTO);

    void setAccountId(int accountId , int studentId);

    List<StudentListDTO> getAllStudent(int classId);

    List<StudentListDTO> searchStudentByNameAndHometown(int index, String name, String hometown);

    StudentViewDTO getStudentById(int id);

    void deleteStudent(int id);

    StudentDeleteDTO getStudentFullById(int id);

    void updateStudent(StudentUpdateDTO studentUpdateDTO);

    List<StudentListDTO> getAllStudentByClassId(int classId, int index);

    List<StudentListDTO> getAllStudentCurrentYear(int yearId,int index);

    List<StudentListDTO> getAllStudentPage(int yearId);

}
