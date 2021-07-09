package com.a0720i1.project_be.services;

import org.springframework.stereotype.Service;
import com.a0720i1.project_be.dto.teacher.TeacherScheduleDTO;

import java.util.List;

@Service
public interface LessonService {
    void deleteAllLessonByScheduleId(int id);
    void insertNewLesson(int lessonTimeId, int scheduleId, int subjectId, int teacherId);

    List<TeacherScheduleDTO> getAllLessonByTeacherUsername(String username);
}
