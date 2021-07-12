package com.a0720i1.project_be.dto.schedule;

public interface LessonShowDto {
    int getStudentClassId();
    int getLessonId();
    int getLessonDate();
    int getLessonNumber();
    int getSubjectId();
    String getSubjectName();
    String getTeacherName();
}
