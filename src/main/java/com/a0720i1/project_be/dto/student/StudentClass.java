package com.a0720i1.project_be.dto.student;

import java.time.LocalDate;

public interface StudentClass {
    int getId() ;
    String getName() ;
    LocalDate getBirthday();
    String getHometown();
}
