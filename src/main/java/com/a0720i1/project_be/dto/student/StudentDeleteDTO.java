package com.a0720i1.project_be.dto.student;

import java.time.LocalDate;

public interface StudentDeleteDTO {
    int getId();
    String getName();
    LocalDate getBirthday();
    String getImageUrl();
    String getEmail();
    String getGender();
    String getHometown();
    String getReligion();
    String getEthnicity();
}
