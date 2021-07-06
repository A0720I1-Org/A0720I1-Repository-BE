package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.dto.HomeRoomClassDTO;
import com.a0720i1.project_be.dto.StudentHomeroomClassDTO;
import com.a0720i1.project_be.repositories.StudentRepository;
import com.a0720i1.project_be.services.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
