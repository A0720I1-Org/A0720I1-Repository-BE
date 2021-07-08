package com.a0720i1.project_be.controllers;

import com.a0720i1.project_be.dto.StudentResultDTO;
import com.a0720i1.project_be.dto.class_student.ClassListDTO;
import com.a0720i1.project_be.dto.class_student.StudentAverageMarkDTO;
import com.a0720i1.project_be.dto.class_student.StudentListDTO;
import com.a0720i1.project_be.dto.class_student.StudentResultUpdateDTO;
import com.a0720i1.project_be.models.ReportCard;
import com.a0720i1.project_be.models.Subject;
import com.a0720i1.project_be.services.SubjectResultService;
import com.a0720i1.project_be.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class SubjectResultController {
    @Autowired
    SubjectResultService subjectResultService;
    @GetMapping("/api/teacher/subject-result/subject")
    public ResponseEntity<List<Subject>> getListSubject() {
        return subjectResultService.findAllSubject().isEmpty() ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(subjectResultService.findAllSubject(),HttpStatus.OK) ;
    }
    @GetMapping("/api/teacher/subject-result/student")
    public ResponseEntity<List<StudentListDTO>> getListStudent(@RequestParam("claStuId") int claStuId) {
        return subjectResultService.getListStudent(claStuId).isEmpty() ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(subjectResultService.getListStudent(claStuId),HttpStatus.OK) ;
    }
    @GetMapping("/api/teacher/subject-result")
    public ResponseEntity<List<StudentResultUpdateDTO>> getListStudentResult(@RequestParam("seReId") int seReId,
                                                                       @RequestParam("claStuId") int claStuId,
                                                                       @RequestParam("subId") int subId) {

        return subjectResultService.getListStudentResult(seReId,claStuId,subId).isEmpty()?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(subjectResultService.getListStudentResult(seReId,claStuId,subId),HttpStatus.OK) ;
    }
    @GetMapping("/api/teacher/subject-result/class")
    public ResponseEntity<List<ClassListDTO>> getListClass() {

        return subjectResultService.findAllClass().isEmpty()?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(subjectResultService.findAllClass(),HttpStatus.OK) ;
    }

    @PutMapping("/api/teacher/subject-result/subject-result")
    public ResponseEntity<?> updateSubjectResult(@RequestParam("seReId") int seReId,
                                                 @RequestParam("claStuId") int claStuId,
                                                 @RequestParam("subId") int subId,
                                                 @RequestBody ArrayList<StudentResultUpdateDTO> studentResultDTOS) {
        for(StudentResultUpdateDTO studentResultDTO : studentResultDTOS) {
            if(subjectResultService.getMarkId(seReId,claStuId,subId,studentResultDTO) == null) {
                return ResponseEntity.badRequest().body("Cập nhật điểm ko thành công");
            }
            subjectResultService.updateMark(seReId,claStuId,subId,studentResultDTO);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/api/public/subject-result/average")
    public ResponseEntity<List<StudentAverageMarkDTO>> getAverage(@RequestParam("seReId") int seReId,
                                                            @RequestParam("claStuId") int claStuId) {
        return new ResponseEntity<>(subjectResultService.getStudentAverageMark(seReId,claStuId),HttpStatus.OK);
    }
}
