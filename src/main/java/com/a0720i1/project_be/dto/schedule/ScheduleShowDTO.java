package com.a0720i1.project_be.dto.schedule;

import lombok.Data;

@Data
public class ScheduleShowDTO {
    private int studentClassId;
    private int scheduleId;
    private int lessonId;
    private int lessonDate;
    private int lessonNumber;
    private int subjectId;
    private String subjectName;
    private String teacherName;
}
