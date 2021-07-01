package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.dto.teacher.TeacherScheduleDTO;
import com.a0720i1.project_be.repositories.LessonRepository;
import com.a0720i1.project_be.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    private LessonRepository lessonRepository;
    @Override
    public List<TeacherScheduleDTO> getAllLessonByTeacherUsername(String username) {
        return lessonRepository.getAllLessonByTeacherUsername(username);
    }
}
