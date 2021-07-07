package com.a0720i1.project_be.controllers;


import com.a0720i1.project_be.dto.class_student.ClassStudentListDTO;
import com.a0720i1.project_be.dto.class_student.ClassStudentNameDTO;
import com.a0720i1.project_be.dto.class_student.ClassStudentTeacherListDTO;
import com.a0720i1.project_be.models.SchoolYear;
import com.a0720i1.project_be.services.SchoolYearService;
import com.a0720i1.project_be.services.StudentClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClassController {
    @Autowired
    private StudentClassService studentClassService;
    @Autowired
    private SchoolYearService schoolYearService;

    @GetMapping("/api/admin/teacher/teacher-list")
    public ResponseEntity<List<ClassStudentTeacherListDTO>> getAllTeacher(){
        List<ClassStudentTeacherListDTO> teacherList = this.studentClassService.getAllTeacher();
        if (teacherList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(teacherList, HttpStatus.OK);
    }

    @GetMapping("/api/admin/class-student/class-student-list")
    public ResponseEntity<List<ClassStudentNameDTO>> getAllClassName(){
        List<ClassStudentNameDTO> classNameList = this.studentClassService.getAllClassName();
        if (classNameList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(classNameList, HttpStatus.OK);
    }

    @GetMapping("/api/admin/school-year/school-year-list")
    public ResponseEntity<List<SchoolYear>> getAllSchoolYear(){
        List<SchoolYear> schoolYearList = this.schoolYearService.findAll();
        if (schoolYearList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(schoolYearList, HttpStatus.OK);
    }

    @GetMapping("/api/admin/student/student-list")
    public ResponseEntity<List<ClassStudentListDTO>> getAllStudent(){
        List<ClassStudentListDTO> classStudentListDTOS = this.studentClassService.getAllStudent();
        if (classStudentListDTOS.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(classStudentListDTOS, HttpStatus.OK);
    }










}
