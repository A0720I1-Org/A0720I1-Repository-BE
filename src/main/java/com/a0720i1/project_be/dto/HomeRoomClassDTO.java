package com.a0720i1.project_be.dto;

import java.time.LocalDate;

public interface HomeRoomClassDTO {
    int getId() ;
    String getName() ;
    String getImageUrl() ;
    LocalDate getBirthday();
    String getHometown();
    String getEthnicity();
    String getGender();
    String getReligion();
    String getEmail();
    String getClassName();
}
