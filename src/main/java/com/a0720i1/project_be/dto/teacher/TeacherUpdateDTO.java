package com.a0720i1.project_be.dto.teacher;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherUpdateDTO {
    int id;
    String name;
    LocalDate birthday;
    String address;
    String phone;
    String email;
    String gender;
    String hometown;
    String level;
    String position;
}
