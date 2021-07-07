package com.a0720i1.project_be.dto.class_student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentDTO {
    private int id;
    private String name;
    private LocalDate birthday;
    private String gender;
    private String hometown;
    private String email;
    private String religion;
    private String ethnicity;
}
