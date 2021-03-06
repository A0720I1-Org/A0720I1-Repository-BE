package com.a0720i1.project_be.dto.class_student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public interface MarkDTO {
    int getStudentId();
    Double getMarkCol1();
    Double getMarkCol2();
    Double getMarkCol3();
    int getMultiplier();
    int getSubjectId();
}
