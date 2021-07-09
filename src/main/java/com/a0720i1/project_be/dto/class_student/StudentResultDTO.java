package com.a0720i1.project_be.dto.class_student;

import java.time.LocalDate;

public interface StudentResultDTO {
    int getStudentId();
    String getName();
    Double getMarkCol1();
    Double getMarkCol2();
    Double getMarkCol3();
    Integer getMultiplier();
    LocalDate getBirthday();
}
