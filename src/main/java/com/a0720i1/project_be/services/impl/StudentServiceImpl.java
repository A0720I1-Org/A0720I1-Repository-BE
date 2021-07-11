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
                createStudentDTO.getHometown(), createStudentDTO.getEmail() , createStudentDTO.getReligion() , createStudentDTO.getEthnicity());
    }
}
