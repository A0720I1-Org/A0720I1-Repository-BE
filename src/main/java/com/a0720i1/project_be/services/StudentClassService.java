package com.a0720i1.project_be.services;

import com.a0720i1.project_be.dto.schedule.ScheduleClassDTO;
import com.a0720i1.project_be.models.StudentClass;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface StudentClassService {
    List<StudentClass> getAll();

    List<StudentClass> getByYearIdAndGradeId(int yearId, int gradeId);

    ScheduleClassDTO getClassNameByClassId(int id);

    StudentClass getStudentClassById(int id);
}
