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

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getHometown() {
        return hometown;
    }

    public String getLevel() {
        return level;
    }

    public String getPosition() {
        return position;
    }
}
