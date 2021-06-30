package com.a0720i1.project_be.dto.student;

import lombok.Data;

@Data
public class ClassSearchDTO {
    int yearId;
    int gradeId;


    public ClassSearchDTO(int yearId, int gradeId) {
        this.yearId = yearId;
        this.gradeId = gradeId;
    }

    public void setYearId(int yearId) {
        this.yearId = yearId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getYearId() {
        return yearId;
    }

    public int getGradeId() {
        return gradeId;
    }
}
