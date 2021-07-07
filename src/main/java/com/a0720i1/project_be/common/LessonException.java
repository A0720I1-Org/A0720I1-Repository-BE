package com.a0720i1.project_be.common;

import com.a0720i1.project_be.dto.schedule.AssignedTeacherDTO;

import java.util.List;

public class LessonException extends Exception {
    public LessonException(String message) {
        super(message);
    }
}
