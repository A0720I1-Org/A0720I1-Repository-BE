package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.models.StudentClass;
import com.a0720i1.project_be.repositories.StudentClassRepository;
import com.a0720i1.project_be.services.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentClassServiceImpl implements StudentClassService {

    @Autowired
    private StudentClassRepository studentClassRepository;
    @Override
    public List<StudentClass> getAll(int yearId, int gradeId) {
        return studentClassRepository.findByYearIdAndGradeId(yearId,gradeId);
    }
}
