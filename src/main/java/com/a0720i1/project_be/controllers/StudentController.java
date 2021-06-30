package com.a0720i1.project_be.controllers;

import com.a0720i1.project_be.dto.student.*;
import com.a0720i1.project_be.models.Grade;
import com.a0720i1.project_be.models.SchoolYear;
import com.a0720i1.project_be.models.StudentClass;
import com.a0720i1.project_be.services.GradeService;
import com.a0720i1.project_be.services.SchoolYearService;
import com.a0720i1.project_be.services.StudentClassService;
import com.a0720i1.project_be.services.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private SchoolYearService schoolYearService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentClassService studentClassService;

    @GetMapping("/api/public/school-year/get-school-year-list")
    public ResponseEntity<List<SchoolYear>> getAllSchoolYear() {
        List<SchoolYear> schoolYearList = this.schoolYearService.getAll();
        if (schoolYearList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(schoolYearList, HttpStatus.OK);
        }
    }

    @GetMapping("/api/public/grade/get-grade-list")
    public ResponseEntity<List<Grade>> getAllGrade() {
        List<Grade> gradeList = this.gradeService.getAll();
        if (gradeList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(gradeList, HttpStatus.OK);
        }
    }

    @PostMapping("/api/public/class/get-class-list-by-year-grade")
    public ResponseEntity<List<StudentClass>> getAllClasses(@RequestBody ClassSearchDTO classSearchDTO) {
        List<StudentClass> studentClassList = this.studentClassService.getAll(classSearchDTO.getYearId(), classSearchDTO.getGradeId());
        if (studentClassList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(studentClassList, HttpStatus.OK);
        }
    }

    @GetMapping("/api/student")
    public ResponseEntity<List<StudentListDTO>> getPageAllStudent(int index) {
        System.out.println(index);
        List<StudentListDTO> studentListPage = this.studentService.getPageAllStudent(index);
        if (studentListPage.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(studentListPage, HttpStatus.OK);
    }

    @GetMapping("/api/student/list")
    public ResponseEntity<List<StudentListDTO>> getAllStudent() {
        List<StudentListDTO> studentListDTOS = this.studentService.getAllStudent();
        if (studentListDTOS.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(studentListDTOS, HttpStatus.OK);
    }

    @GetMapping("/api/student/find/{id}")
    public ResponseEntity<StudentViewDTO> getStudentById(@PathVariable int id) {
        StudentViewDTO studentViewDTO = this.studentService.getStudentById(id);
        if (studentViewDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(studentViewDTO, HttpStatus.OK);
    }

    @PutMapping("/api/student/update")
    public ResponseEntity<StudentUpdateDTO> updateStudent(@RequestBody StudentUpdateDTO studentUpdateDTO) {
        studentService.updateStudent(studentUpdateDTO);
        return new ResponseEntity<>(studentUpdateDTO, HttpStatus.OK);
    }

    @GetMapping("/api/student/findDelete/{id}")
    public ResponseEntity<StudentDeleteDTO> getStudentFullById(@PathVariable int id) {
        StudentDeleteDTO studentDeleteDTO = this.studentService.getStudentFullById(id);
        if (studentDeleteDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(studentDeleteDTO, HttpStatus.OK);
    }


    @DeleteMapping("/api/student/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
        this.studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
