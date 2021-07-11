package com.a0720i1.project_be.controllers;

import com.a0720i1.project_be.common.InvalidClassNameException;
import com.a0720i1.project_be.dto.student_class.ClassCreateDTO;
import com.a0720i1.project_be.dto.student_class.ClassCreateStudentDTO;
import com.a0720i1.project_be.services.StudentClassService;
import com.a0720i1.project_be.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ClassController {
    @Autowired
    StudentClassService studentClassService;

    @Autowired
    StudentService studentService;

    @PostMapping("/api/admin/student-class/create-class")
    public ResponseEntity<?> createClass(@RequestBody ClassCreateDTO classCreateDTO) throws InvalidClassNameException {
        if (studentClassService.getAllClassByName(classCreateDTO.getName()).size() > 0) {
            throw new InvalidClassNameException("Tên lớp không khả dụng");
        }
        studentClassService.createClass(classCreateDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/api/public/student-class/get-student-list-by-class-id/{classId}")
    public ResponseEntity<List<ClassCreateStudentDTO>> getStudentListByClassId(@PathVariable int classId) {
        List<ClassCreateStudentDTO> studentList = studentService.getAllStudentByClassId(classId);
        if (studentList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

}
