package com.a0720i1.project_be.services;

import org.springframework.stereotype.Service;

@Service
public interface LessonService {
    void deleteAllLessonByScheduleId(int id);
    void insertNewLesson(int lessonTimeId, int scheduleId, int subjectId, int teacherId);
}
