package com.a0720i1.project_be.controllers;

import com.a0720i1.project_be.dto.schedule.LessonDTO;
import com.a0720i1.project_be.dto.schedule.StudentClassDTO;
import com.a0720i1.project_be.models.Grade;
import com.a0720i1.project_be.models.SchoolYear;
import com.a0720i1.project_be.models.StudentClass;
import com.a0720i1.project_be.services.impl.GradeServiceImpl;
import com.a0720i1.project_be.services.impl.LessonServiceImpl;
import com.a0720i1.project_be.services.impl.SchoolYearServiceImpl;
import com.a0720i1.project_be.services.impl.StudentClassServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScheduleController {
    @Autowired
    private LessonServiceImpl lessonServiceImpl;
    @Autowired
    private SchoolYearServiceImpl schoolYearServiceImpl;
    @Autowired
    private GradeServiceImpl gradeServiceImpl;
    @Autowired
    private StudentClassServiceImpl studentClassServiceImpl;

    @GetMapping("/api/pubic/schedule/get-schedule")
    public ResponseEntity<List<LessonDTO>> getSchedule(@RequestParam("studentClassId") int studentClassId) {
        List<LessonDTO> schedule = this.lessonServiceImpl.getAllLesson(studentClassId);
        if (schedule.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(schedule, HttpStatus.OK);
    }

    @GetMapping("/api/pubic/school-year/get-list-school-year")
    public ResponseEntity<List<SchoolYear>> getAllSchoolYear() {
        List<SchoolYear> schoolYearList = this.schoolYearServiceImpl.findAll();
        if (schoolYearList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(schoolYearList, HttpStatus.OK);
    }

    @GetMapping("/api/pubic/grade/get-list-grade")
    public ResponseEntity<List<Grade>> getAllGrade() {
        List<Grade> gradeList = this.gradeServiceImpl.getAll();
        if ((gradeList.isEmpty())) {
            return new ResponseEntity<>((HttpStatus.NO_CONTENT));
        }
        return new ResponseEntity<>(gradeList, HttpStatus.OK);
    }
    @PostMapping("/api/public/student-class/get-list-student-class")
    public ResponseEntity<List<StudentClassDTO>> getStudentClass(@RequestParam("gradeId") int gradeId,
                                                              @RequestParam("schoolYearId") int schoolYearId) {
        List<StudentClassDTO> studentClassList = this.studentClassServiceImpl.getStudentClass(gradeId,schoolYearId);
        if (studentClassList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(studentClassList, HttpStatus.OK);
    }
}
