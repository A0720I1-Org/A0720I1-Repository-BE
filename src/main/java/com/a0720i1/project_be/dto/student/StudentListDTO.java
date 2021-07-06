package com.a0720i1.project_be.dto.student;

import java.time.LocalDate;

public interface StudentListDTO {
    int getId() ;
    String getName() ;
    String getImageUrl() ;
    LocalDate getBirthday();
    String getHometown();
}