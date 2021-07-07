package com.a0720i1.project_be.services;

import org.springframework.stereotype.Service;

@Service
public interface LessonTimeService {
    int getLessonIdByLessonDateAndLessonNumber(int lessonDate, int lessonNumber);
}
