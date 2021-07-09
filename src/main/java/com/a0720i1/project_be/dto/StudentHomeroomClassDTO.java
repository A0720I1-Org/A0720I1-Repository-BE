package com.a0720i1.project_be.dto;

import java.time.LocalDate;

public interface StudentHomeroomClassDTO {
    int getId() ;
    String getName() ;
    String getImageUrl() ;
    LocalDate getBirthday();
    String getHometown();
    String getEthnicity();
    String getGender();
    String getReligion();
    String getEmail();
}
