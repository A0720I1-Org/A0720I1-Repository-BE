package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.dto.schedule.LessonDTO;
import com.a0720i1.project_be.repositories.LessonRepository;
import com.a0720i1.project_be.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LessonServiceImpl implements LessonService {
    @Autowired
    LessonRepository lessonRepository;

    @Override
    public List<LessonDTO> getAllLesson(int studentClassId) {
        return lessonRepository.getAllLesson(studentClassId);
    }
}
