package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.dto.class_student.CreateStudentDTO;


import com.a0720i1.project_be.dto.HomeRoomClassDTO;
import com.a0720i1.project_be.dto.StudentHomeroomClassDTO;
import com.a0720i1.project_be.dto.student_class.ClassCreateStudentDTO;
import com.a0720i1.project_be.repositories.StudentRepository;
import com.a0720i1.project_be.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import java.util.List;
import com.a0720i1.project_be.dto.student.StudentDeleteDTO;
import com.a0720i1.project_be.dto.student.StudentListDTO;
import com.a0720i1.project_be.dto.student.StudentUpdateDTO;
import com.a0720i1.project_be.dto.student.StudentViewDTO;
import com.a0720i1.project_be.repositories.StudentRepository;
import com.a0720i1.project_be.services.StudentService;
import java.util.stream.Collectors;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<HomeRoomClassDTO> getStudentByTeacherUsername(String username) {
        return studentRepository.getStudentByTeacherUsername(username);
    }

    @Override
    public List<HomeRoomClassDTO> getPageStudentByTeacherUsername(int index, String username) {
        return studentRepository.getPageStudentByTeacherUsername(username, index);
    }

    @Override
    public StudentHomeroomClassDTO getInforStudent(int id) {
        return studentRepository.getInfoStudent(id);
    }

    @Override
    public List<HomeRoomClassDTO> searchStudentByName(String name, int index) {
        return studentRepository.searchStudentByName(name, index);
    }

    @Override
    public List<ClassCreateStudentDTO> getAllStudentByClassId(int classId) {
        return studentRepository.getAllStudentByClassId(classId);
    }

    @Override
    public void createStudent(String name, LocalDate birthday, String gender, String hometown, String email,
                              String religion, String ethnicity, String imageUrl) {
        this.studentRepository.createStudent(name, birthday, gender, hometown, email, religion, ethnicity, imageUrl);
    }

    @Override
    public Integer getIdByEmail(String email) {
        return studentRepository.getIdByEmail(email);
    }

    @Override
    public void setAccountId(int accountId, int studentId) {
        this.studentRepository.setAccountId(accountId, studentId);
    }

    public void createStudentDTO(CreateStudentDTO createStudentDTO) {
        this.studentRepository.createStudentDTO(createStudentDTO.getName(), createStudentDTO.getBirthday(), createStudentDTO.getGender(),
                createStudentDTO.getHometown(), createStudentDTO.getEmail(), createStudentDTO.getReligion(), createStudentDTO.getEthnicity());
    }

    public List<StudentListDTO> getAllStudent(int classId) {
        return this.studentRepository.getAllStudent(classId);
    }

    @Override
    public List<StudentListDTO> searchStudentByNameAndHometown(int index, String name, String hometown) {
        return this.studentRepository.searchStudentByNameAndHometown(index, name, hometown);
    }

    @Override
    public StudentViewDTO getStudentById(int id) {
        return this.studentRepository.getStudentById(id);
    }

    @Override
    public void deleteStudent(int id) {
        this.studentRepository.deleteStudent(id);
    }

    @Override
    public StudentDeleteDTO getStudentFullById(int id) {
        return studentRepository.getStudentFullById(id);
    }


    @Override
    public void updateStudent(StudentUpdateDTO studentUpdateDTO) {
        this.studentRepository.updateStudentDTO(studentUpdateDTO.getName(), studentUpdateDTO.getBirthday(),
                studentUpdateDTO.getGender(), studentUpdateDTO.getHometown(), studentUpdateDTO.getEthnicity(),
                studentUpdateDTO.getReligion(), studentUpdateDTO.getImageUrl(), studentUpdateDTO.getEmail(),
                studentUpdateDTO.getId());
    }

    @Override
    public List<StudentListDTO> getAllStudentByClassId(int classId,int index) {
        return studentRepository.getAllStudentByClassId(classId,index);
    }


    @Override
    public List<StudentListDTO> getAllStudentCurrentYear(int yearId, int index) {
        return studentRepository.getAllStudentCurrentYear(yearId,index);
    }
    @Override
    public List<StudentListDTO> getAllStudentPage(int yearId) {
        return studentRepository.getAllStudentPage(yearId);

    }
}
