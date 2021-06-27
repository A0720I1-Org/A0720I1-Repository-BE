package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.dto.StudentResultDTO;
import com.a0720i1.project_be.repositories.SubjectResultRepository;
import com.a0720i1.project_be.services.SubjectResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectResultServiceImpl implements SubjectResultService {
    @Autowired
    SubjectResultRepository subjectResultRepository;

    @Override
    public List<StudentResultDTO> findResultDTO(int stuClaId, int seReId, int subId) {
        return subjectResultRepository.findStudentResult(stuClaId,seReId,subId);
    }
}
