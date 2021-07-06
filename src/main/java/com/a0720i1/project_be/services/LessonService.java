package com.a0720i1.project_be.services;

import com.a0720i1.project_be.dto.teacher.TeacherScheduleDTO;

import java.util.List;

public interface LessonService {
    List<TeacherScheduleDTO> getAllLessonByTeacherUsername(String username);
}
