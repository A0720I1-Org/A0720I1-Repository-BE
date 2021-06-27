package com.a0720i1.project_be.controllers;

import com.a0720i1.project_be.dto.StudentResultDTO;
import com.a0720i1.project_be.models.Subject;
import com.a0720i1.project_be.services.SubjectResultService;
import com.a0720i1.project_be.services.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/subject-result")
@RestController
public class SubjectResultController {
    @Autowired
    SubjectService subjectService ;
    @Autowired
    SubjectResultService subjectResultService;

    @GetMapping("/subject")
    public ResponseEntity<List<Subject>> getListSubject() {
        return subjectService.getListSubject().isEmpty() ?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(subjectService.getListSubject(),HttpStatus.OK) ;
    }
    @GetMapping("")
    public ResponseEntity<List<StudentResultDTO>> getListStudentResult(@RequestParam("claStuId") int claStuId,
                                                                       @RequestParam("seReId") int seReId,
                                                                       @RequestParam("subId") int subId) {

        return subjectResultService.findResultDTO(claStuId,seReId,subId).isEmpty()?
                new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(subjectResultService.findResultDTO(claStuId,seReId,subId),HttpStatus.OK) ;
    }
}
