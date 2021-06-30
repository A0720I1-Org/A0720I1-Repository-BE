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
    public List<HomeRoomClassDTO> getStudentByClassId(String username) {
        Integer classId = this.getClassIdByUsernameTeacher(username);
        return studentRepository.getStudentByClassId(classId);
    }

    @Override
    public List<HomeRoomClassDTO> getPageStudentByClassId(int index, String username) {
        Integer classId = this.getClassIdByUsernameTeacher(username);
        return studentRepository.getPageStudentByClassId(classId, index);
    }

    @Override
    public Integer  getClassIdByUsernameTeacher(String username) {
        if(studentRepository.getIdClassByUsernameTeacher(username) == null) {
            return null ;
        }
        return studentRepository.getIdClassByUsernameTeacher(username);
    }

    @Override
    public StudentHomeroomClassDTO getInforStudent(int id) {
        return studentRepository.getInfo(id);
    }

    @Override
    public List<HomeRoomClassDTO> searchStudentByName(String name, int index) {
        return studentRepository.searchStudentByName(name, index);
    }
}
