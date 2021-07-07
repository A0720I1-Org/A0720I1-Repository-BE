package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.models.SchoolYear;
import com.a0720i1.project_be.repositories.SchoolYearRepository;
import com.a0720i1.project_be.services.SchoolYearService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolYearServiceImpl implements SchoolYearService {
    @Autowired
    SchoolYearRepository schoolYearRepository;
    @Override
    public List<SchoolYear> getAll() {
        return schoolYearRepository.findAll();
    }
}

