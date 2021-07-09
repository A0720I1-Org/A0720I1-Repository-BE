package com.a0720i1.project_be.services;

import com.a0720i1.project_be.models.StudentClass;
import java.util.List;
import com.a0720i1.project_be.dto.schedule.ScheduleClassDTO;
import org.springframework.stereotype.Service;



@Service
public interface StudentClassService {
    List<StudentClass> getAll();

    List<StudentClass> getByYearIdAndGradeId(int yearId, int gradeId);

    ScheduleClassDTO getClassNameByClassId(int id);

    StudentClass getStudentClassById(int id);

    List<StudentClass> getAll(int yearId, int gradeId);
}
