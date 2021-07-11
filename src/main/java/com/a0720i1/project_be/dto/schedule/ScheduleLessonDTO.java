package com.a0720i1.project_be.dto.schedule;

import lombok.Data;

@Data
public class ScheduleLessonDTO {
    private int lessonDate;
    private int lessonNumber;
    private int subjectId;
    private int teacherId;
}
