package com.a0720i1.project_be.services;

import com.a0720i1.project_be.dto.StudentResultDTO;
import com.a0720i1.project_be.models.Subject;

import java.util.List;

public interface SubjectService {
    List<Subject> getListSubject();
    List<StudentResultDTO> findResultDTO();
}
