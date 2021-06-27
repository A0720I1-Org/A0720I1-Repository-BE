package com.a0720i1.project_be.dto.teacher;

import java.time.LocalDate;

public interface TeacherViewDTO {
    int getId();
    String getName();
    LocalDate getBirthday();
    String getAddress();
    String getPhone();
    String getEmail();
    String getGender();
    String getHometown();
    String getLevel();
    String getPosition();
    String getStudentClass();
}
