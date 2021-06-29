package com.a0720i1.project_be.services;

import com.a0720i1.project_be.dto.schedule.StudentClassDTO;
import com.a0720i1.project_be.models.StudentClass;

import java.util.List;

public interface StudentClassService {
    List<StudentClassDTO> getStudentClass(int gradeId, int schoolYearId);
}
