package com.a0720i1.project_be.dto.teacher;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TeacherCreateDTO {
    String username;
    String password;
    String name;
    LocalDate birthday;
    String gender;
    String email;
    String imageUrl;
}
