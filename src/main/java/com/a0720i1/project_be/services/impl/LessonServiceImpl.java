package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.repositories.LessonRepository;
import com.a0720i1.project_be.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    LessonRepository lessonRepository;

    @Override
    public void deleteAllLessonByScheduleId(int id) {
        lessonRepository.deleteAllLessonByScheduleId(id);
    }

    @Override
    public void insertNewLesson(int lessonTimeId, int scheduleId, int subjectId, int teacherId) {
        lessonRepository.insertNewLesson(lessonTimeId, scheduleId, subjectId, teacherId);
    }
}
