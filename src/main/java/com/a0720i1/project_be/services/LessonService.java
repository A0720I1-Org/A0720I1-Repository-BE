package com.a0720i1.project_be.services;

import com.a0720i1.project_be.dto.schedule.LessonDTO;

import java.util.List;

public interface LessonService {
    List<LessonDTO> getAllLesson(int studentClassId);
}
