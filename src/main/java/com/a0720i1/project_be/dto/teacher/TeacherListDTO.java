package com.a0720i1.project_be.dto.teacher;

import java.time.LocalDate;

public interface TeacherListDTO {
    int getId() ;
    String getName() ;
    LocalDate getBirthday();
    String getAddress() ;
    String getPhone() ;
}
