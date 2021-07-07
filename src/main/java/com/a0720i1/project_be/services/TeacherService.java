package com.a0720i1.project_be.services;

import com.a0720i1.project_be.dto.schedule.ScheduleTeacherDTO;
import com.a0720i1.project_be.dto.teacher.TeacherListDTO;
import com.a0720i1.project_be.dto.teacher.TeacherUpdateDTO;
import com.a0720i1.project_be.dto.teacher.TeacherViewDTO;
import com.a0720i1.project_be.models.Teacher;

import java.time.LocalDate;
import java.util.List;

public interface TeacherService {
    List<TeacherListDTO> getPageAllTeacher(int index);

    List<TeacherListDTO> getListTeacher();

    TeacherViewDTO getTeacherById(int id);

    void updateTeacher(TeacherUpdateDTO teacherUpdateDTO);

    List<TeacherListDTO> searchTeacherByNameAndAddress(int index, String name, String address);

    void createTeacher(String name, LocalDate birthday, String gender, String email, String imageUrl, int accountId);

    Teacher getTeacherByEmail(String email);

    List<ScheduleTeacherDTO> getAllTeacherForSchedule();
}
