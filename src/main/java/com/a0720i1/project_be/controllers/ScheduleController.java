package com.a0720i1.project_be.controllers;

import com.a0720i1.project_be.common.AssignedTeacherException;
import com.a0720i1.project_be.common.LessonException;
import com.a0720i1.project_be.dto.schedule.*;
import com.a0720i1.project_be.models.*;
import com.a0720i1.project_be.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class ScheduleController {
    @Autowired
    private SchoolYearService schoolYearService;

    @Autowired
    private GradeService gradeService;

    @Autowired
    private StudentClassService studentClassService;

    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private LessonService lessonService;

    @Autowired
    private LessonTimeService lessonTimeService;

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

    @GetMapping("/api/public/class/get-class-list")
    public ResponseEntity<List<StudentClass>> getAllClasses() {
        List<StudentClass> studentClassList = this.studentClassService.getAll();
        if (studentClassList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(studentClassList, HttpStatus.OK);
        }
    }

    @GetMapping("/api/public/class/get-class-list-by-year-grade")
    public ResponseEntity<List<StudentClass>> getAllClassesByGradeAndYear(@RequestParam int yearId, @RequestParam int gradeId) {
        List<StudentClass> studentClassList = this.studentClassService.getByYearIdAndGradeId(yearId, gradeId);
        if (studentClassList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(studentClassList, HttpStatus.OK);
        }
    }

    @GetMapping("/api/public/schedule/show-schedule/{classId}")
    public ResponseEntity<List<LessonShowDto>> getScheduleByClassId(@PathVariable int classId) {
        List<LessonShowDto> schedule = this.scheduleService.getScheduleByClassId(classId);
        if (schedule == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(schedule, HttpStatus.OK);
        }
    }

    @GetMapping("/api/public/schedule/subject-teacher-list/{classId}")
    public ResponseEntity<Set<TeacherSubjectDTO>> getTeacherSubjectListByClassId(@PathVariable int classId) {
        Set<TeacherSubjectDTO> subjectList = this.scheduleService.getTeacherSubjectByStudentClassId(classId);
        if (subjectList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(subjectList, HttpStatus.OK);
        }
    }
    @GetMapping("/api/public/schedule/subject-list")
    public ResponseEntity<List<ScheduleSubjectDTO>> getAllSubject() {
        List<ScheduleSubjectDTO> subjectList = this.subjectService.getAllSubject();
        if (subjectList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(subjectList, HttpStatus.OK);
        }
    }

    @GetMapping("/api/public/schedule/get-class-name-by-id/{id}")
    public ResponseEntity<ScheduleClassDTO> getAllSubject(@PathVariable int id) {
        ScheduleClassDTO className = this.studentClassService.getClassNameByClassId(id);
        if (className == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(className, HttpStatus.OK);
        }
    }

    @GetMapping("/api/public/schedule/get-teacher-list")
    public ResponseEntity<List<ScheduleTeacherDTO>> getAllTeacher() {
        List<ScheduleTeacherDTO> teacherList = this.teacherService.getAllTeacherForSchedule();
        if (teacherList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(teacherList, HttpStatus.OK);
        }
    }

    @PostMapping("/api/admin/schedule/update-schedule/{classId}")
    @Transactional
    public ResponseEntity<?> updateSchedule(@PathVariable int classId, @RequestBody List<ScheduleLessonDTO> scheduleLessonDTOList) throws LessonException, AssignedTeacherException {
        List<AssignedTeacherDTO> assignedTeacherDTOList = new ArrayList<>();
        int scheduleId = this.scheduleService.getScheduleIdByClassId(classId);
        int schoolYearId = this.studentClassService.getStudentClassById(classId).getSchoolYear().getId();
        if (scheduleId != 0) {
            this.lessonService.deleteAllLessonByScheduleId(scheduleId);
        } else {
            this.scheduleService.createNewSchedule(classId);
            scheduleId = this.scheduleService.getScheduleIdByClassId(classId);
        }
        for (ScheduleLessonDTO lesson: scheduleLessonDTOList) {
            int lessonTimeId = lessonTimeService.getLessonIdByLessonDateAndLessonNumber(lesson.getLessonDate(), lesson.getLessonNumber());
            if (scheduleService.getAllAssignedTeacherByLessonTimeId(schoolYearId, lesson.getTeacherId(), lessonTimeId) != null) {
                assignedTeacherDTOList.addAll(scheduleService.getAllAssignedTeacherByLessonTimeId(schoolYearId, lesson.getTeacherId(), lessonTimeId));
            }

        }
        if (assignedTeacherDTOList.size() == 0){
            for (ScheduleLessonDTO lesson: scheduleLessonDTOList) {
                int lessonTimeId = lessonTimeService.getLessonIdByLessonDateAndLessonNumber(lesson.getLessonDate(), lesson.getLessonNumber());
                try {
                    lessonService.insertNewLesson(lessonTimeId, scheduleId, lesson.getSubjectId(), lesson.getTeacherId());
                } catch (Exception e) {
                    throw new LessonException("Không thể tạo thời khóa biểu");
                }
            }
        } else {
            throw new AssignedTeacherException("Trùng lịch dạy", assignedTeacherDTOList);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
