package com.a0720i1.project_be.services;

import com.a0720i1.project_be.dto.schedule.TeacherDTO;
import com.a0720i1.project_be.dto.teacher.TeacherListDTO;
import com.a0720i1.project_be.dto.teacher.TeacherUpdateDTO;
import com.a0720i1.project_be.dto.teacher.TeacherViewDTO;

import java.util.List;

public interface TeacherService {
    List<TeacherListDTO> getPageAllTeacher(int index);

    List<TeacherListDTO> getListTeacher();

    TeacherViewDTO getTeacherById(int id);

    void updateTeacher(TeacherUpdateDTO teacherUpdateDTO);

    List<TeacherListDTO> searchTeacherByNameAndAddress(int index, String name, String address);

    List<TeacherDTO> getTeacherForSubject(int studentClassId);
}
