package com.a0720i1.project_be.services;

import com.a0720i1.project_be.dto.StudentResultDTO;
import com.a0720i1.project_be.dto.schedule.ScheduleSubjectDTO;
import com.a0720i1.project_be.models.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubjectService {
    List<Subject> getListSubject();

    List<StudentResultDTO> findResultDTO();

    List<ScheduleSubjectDTO> getAllSubject();
}
