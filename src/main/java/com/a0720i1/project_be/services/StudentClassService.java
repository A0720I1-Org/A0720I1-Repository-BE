package com.a0720i1.project_be.services;

import com.a0720i1.project_be.models.StudentClass;

import java.util.List;

public interface StudentClassService {
    List<StudentClass> getAll(int yearId, int gradeId);
}
