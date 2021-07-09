package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.dto.StudentResultDTO;
import com.a0720i1.project_be.dto.schedule.ScheduleSubjectDTO;
import com.a0720i1.project_be.models.Subject;
import com.a0720i1.project_be.repositories.SubjectRepository;
import com.a0720i1.project_be.repositories.SubjectResultRepository;
import com.a0720i1.project_be.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    SubjectResultRepository subjectResultRepository;
    @Override
    public List<Subject> getListSubject() {
        return subjectRepository.findAll();
    }
    @Override
    public List<StudentResultDTO> findResultDTO() {
        return subjectResultRepository.findStudentResult(1,2,3);
    }

    @Override
    public List<ScheduleSubjectDTO> getAllSubject() {
        return subjectRepository.getAllSubject();
    }
}
