package com.a0720i1.project_be.dto.class_student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


public interface StudentListDTO {
    int getId();
    String getName();
    LocalDate getBirthday();
}
