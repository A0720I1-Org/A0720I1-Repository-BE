package com.a0720i1.project_be.dto;

import java.time.LocalDate;

public interface StudentResultDTO {
    String getStudentName();
    Double getMarkCol1();
    Double getMarkCol2();
    Double getMarkCol3();
    Integer getMultiplier();
    LocalDate getBirthday();
}
