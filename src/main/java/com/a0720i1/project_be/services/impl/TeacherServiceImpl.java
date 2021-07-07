package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.dto.schedule.ScheduleTeacherDTO;
import com.a0720i1.project_be.dto.teacher.TeacherListDTO;
import com.a0720i1.project_be.dto.teacher.TeacherUpdateDTO;
import com.a0720i1.project_be.dto.teacher.TeacherViewDTO;
import com.a0720i1.project_be.models.Teacher;
import com.a0720i1.project_be.repositories.TeacherRepository;
import com.a0720i1.project_be.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Override
    public List<TeacherListDTO> getPageAllTeacher(int index) {
        return this.teacherRepository.getPageAllTeacher(index);
    }

    @Override
    public List<TeacherListDTO> getListTeacher() {
        return this.teacherRepository.getListTeacher();
    }

    @Override
    public TeacherViewDTO getTeacherById(int id) {
        return this.teacherRepository.getTeacherById(id);
    }

    @Override
    public void updateTeacher(TeacherUpdateDTO teacherUpdateDTO) {
        this.teacherRepository.updateTeacherDTO(teacherUpdateDTO.getAddress(), teacherUpdateDTO.getBirthday(),
                teacherUpdateDTO.getEmail(), teacherUpdateDTO.getGender(), teacherUpdateDTO.getHometown(),
                teacherUpdateDTO.getName(), teacherUpdateDTO.getPhone(), teacherUpdateDTO.getLevel(),
                teacherUpdateDTO.getPosition(), teacherUpdateDTO.getId());
    }

    @Override
    public List<TeacherListDTO> searchTeacherByNameAndAddress(int index, String name, String address) {
        return this.teacherRepository.searchTeacherByNameAndAddress(index, name, address);
    }

    @Override
    public void createTeacher(String name, LocalDate birthday, String gender, String email, String imageUrl, int accountId) {
        this.teacherRepository.createTeacherDTO(name, birthday, gender, email, imageUrl, accountId);
    }

    @Override
    public Teacher getTeacherByEmail(String email) {
        return teacherRepository.getTeacherByEmail(email);
    }

    @Override
    public List<ScheduleTeacherDTO> getAllTeacherForSchedule() {
        return teacherRepository.findAllTeacherForSchedule();
    }
}
