package com.a0720i1.project_be.services;

import com.a0720i1.project_be.dto.StudentResultDTO;

import java.util.List;

public interface SubjectResultService {
    List<StudentResultDTO> findResultDTO(int stuClaId, int seReId, int subId);
}
