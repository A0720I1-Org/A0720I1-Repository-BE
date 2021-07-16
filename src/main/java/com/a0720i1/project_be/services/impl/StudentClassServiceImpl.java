package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.dto.schedule.ScheduleClassDTO;
import com.a0720i1.project_be.dto.student_class.ClassCreateDTO;
import com.a0720i1.project_be.models.StudentClass;
import com.a0720i1.project_be.repositories.StudentClassRepository;
import com.a0720i1.project_be.services.StudentClassService;
import com.a0720i1.project_be.dto.class_student.ClassStudentListDTO;
import com.a0720i1.project_be.dto.class_student.ClassStudentNameDTO;
import com.a0720i1.project_be.dto.class_student.ClassStudentTeacherListDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentClassServiceImpl implements StudentClassService {
    @Autowired
    private StudentClassRepository studentClassRepository;

    @Override
    public List<StudentClass> getAll() {
        return studentClassRepository.findAll();
    }

    @Override
    public List<StudentClass> getByYearIdAndGradeId(int yearId, int gradeId) {
        return studentClassRepository.findByYearIdAndGradeId(yearId, gradeId);
    }

    @Override
    public ScheduleClassDTO getClassNameByClassId(int id) {
        return this.studentClassRepository.findClassNameById(id);
    }

    @Override
    public StudentClass getStudentClassById(int id) {
        return this.studentClassRepository.findById(id).orElse(null);
    }

    @Override
    public void createClass(ClassCreateDTO classCreateDTO) {
        this.studentClassRepository.createClass(classCreateDTO.getSchoolYearId(), classCreateDTO.getGradeId(), classCreateDTO.getName());
    }

    @Override
    public List<StudentClass> getAllClassByName(String name) {
        return studentClassRepository.findAllByName(name);
    }

    @Override
    public List<ClassStudentTeacherListDTO> getAllTeacher() {
        return studentClassRepository.getAllTeacher();
    }

    @Override
    public List<ClassStudentNameDTO> getAllClassName() {
        return studentClassRepository.getAllClassName();
    }

    @Override
    public List<ClassStudentListDTO> getAllStudent() {
        return studentClassRepository.getAllStudent();
    }

    @Override
    public void updateTeacher(int teacherId, int classId) {
        this.studentClassRepository.updateTeacher(teacherId, classId);
    }

    @Override
    public List<StudentClass> getAll(int yearId, int gradeId) {
        return studentClassRepository.findByYearIdAndGradeId(yearId, gradeId);
    }
}
