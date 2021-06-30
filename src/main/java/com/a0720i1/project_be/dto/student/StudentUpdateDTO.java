package com.a0720i1.project_be.dto.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentUpdateDTO {
    int id;
    String name;
    LocalDate birthday;
    String gender;
    String hometown;
    String ethnicity;
    String religion;
    String imageUrl;
    String email;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public String getHometown() {
        return hometown;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public String getReligion() {
        return religion;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getEmail() {
        return email;
    }
}