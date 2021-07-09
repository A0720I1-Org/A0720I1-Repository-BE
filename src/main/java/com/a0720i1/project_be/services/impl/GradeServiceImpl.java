package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.models.Grade;
import com.a0720i1.project_be.repositories.GradeRepository;
import com.a0720i1.project_be.services.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    GradeRepository gradeRepository;

    @Override
    public List<Grade> getAll() {
        return gradeRepository.findAll();
    }
}
