package com.a0720i1.project_be.dto.class_student;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Builder
public class StudentResultUpdateDTO {
    int studentId;
    String name;
    Double markCol1;
    Double markCol2;
    Double markCol3;
    int multiplier;
    LocalDate birthday;

    public StudentResultUpdateDTO(int studentId, String name, Double markCol1, Double markCol2, Double markCol3, int multiplier, LocalDate birthday) {
        this.studentId = studentId;
        this.name = name;
        this.markCol1 = markCol1;
        this.markCol2 = markCol2;
        this.markCol3 = markCol3;
        this.multiplier = multiplier;
        this.birthday = birthday;
    }
}
