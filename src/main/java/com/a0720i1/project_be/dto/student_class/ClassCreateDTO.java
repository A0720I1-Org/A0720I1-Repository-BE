package com.a0720i1.project_be.dto.student_class;

import lombok.Data;

@Data
public class ClassCreateDTO {
    private int schoolYearId;
    private int gradeId;
    private String name;
}
