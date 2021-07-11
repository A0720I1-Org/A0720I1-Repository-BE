package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.repositories.LessonTimeRepository;
import com.a0720i1.project_be.services.LessonTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LessonTimeServiceImpl implements LessonTimeService {
    @Autowired
    LessonTimeRepository lessonTimeRepository;

    @Override
    public int getLessonIdByLessonDateAndLessonNumber(int lessonDate, int lessonNumber) {
        return lessonTimeRepository.findLessonTimeByLessonDateAndLessonNumber(lessonDate, lessonNumber);
    }
}
