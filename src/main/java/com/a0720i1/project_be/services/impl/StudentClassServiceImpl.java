package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.dto.schedule.StudentClassDTO;
import com.a0720i1.project_be.models.StudentClass;
import com.a0720i1.project_be.repositories.StudentClassRepository;
import com.a0720i1.project_be.services.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentClassServiceImpl implements StudentClassService {
    @Autowired
    StudentClassRepository studentClassRepository;
    @Override
    public List<StudentClassDTO> getStudentClass(int gradeId, int schoolYearId) {
        return studentClassRepository.getStudentClass(gradeId, schoolYearId);
    }
}
