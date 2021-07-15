package com.a0720i1.project_be.services;

import com.a0720i1.project_be.dto.schedule.ScheduleClassDTO;
import com.a0720i1.project_be.dto.student_class.ClassCreateDTO;
import com.a0720i1.project_be.models.StudentClass;
import com.a0720i1.project_be.dto.class_student.ClassStudentListDTO;
import com.a0720i1.project_be.dto.class_student.ClassStudentNameDTO;
import com.a0720i1.project_be.dto.class_student.ClassStudentTeacherListDTO;

import java.util.List;

import org.springframework.stereotype.Service;



@Service
public interface StudentClassService {

    List<StudentClass> getAll();

    List<StudentClass> getByYearIdAndGradeId(int yearId, int gradeId);

    ScheduleClassDTO getClassNameByClassId(int id);

    StudentClass getStudentClassById(int id);

    void createClass(ClassCreateDTO classCreateDTO);

    List<StudentClass> getAllClassByName(String name);

    List<ClassStudentTeacherListDTO> getAllTeacher();

    List<ClassStudentNameDTO> getAllClassName();

    List<ClassStudentListDTO> getAllStudent();

    List<StudentClass> getAll(int yearId, int gradeId);
}
