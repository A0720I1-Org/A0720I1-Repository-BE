package com.a0720i1.project_be.controllers;

import com.a0720i1.project_be.dto.class_student.CreateStudentDTO;
import com.a0720i1.project_be.dto.student.*;
import com.a0720i1.project_be.models.Grade;
import com.a0720i1.project_be.models.SchoolYear;
import com.a0720i1.project_be.models.StudentClass;
import com.a0720i1.project_be.services.*;
import com.a0720i1.project_be.services.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRoleService accountRoleService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private ReportCardService reportCardService;


    @GetMapping("/api/public/student/school-year/get-school-year-list")
    public ResponseEntity<List<SchoolYear>> getAllSchoolYear() {
        List<SchoolYear> schoolYearList = this.schoolYearService.getAll();
        if (schoolYearList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(schoolYearList, HttpStatus.OK);
        }
    }

    @GetMapping("/api/public/student/grade/get-grade-list")
    public ResponseEntity<List<Grade>> getAllGrade() {
        List<Grade> gradeList = this.gradeService.getAll();
        if (gradeList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(gradeList, HttpStatus.OK);
        }
    }

    @PostMapping("/api/public/student/class/get-class-list-by-year-grade")
    public ResponseEntity<List<StudentClass>> getAllClasses(@RequestBody ClassSearchDTO classSearchDTO) {
        List<StudentClass> studentClassList = this.studentClassService.getAll(classSearchDTO.getYearId(), classSearchDTO.getGradeId());
        if (studentClassList == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(studentClassList, HttpStatus.OK);
        }
    }

    @GetMapping("/api/student/student/find-student/{id}")
    public ResponseEntity<StudentViewDTO> getStudentById(@PathVariable int id) {
        StudentViewDTO studentViewDTO = this.studentService.getStudentById(id);
        if (studentViewDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(studentViewDTO, HttpStatus.OK);
    }

    @PutMapping("/api/teacher/student/update-student")
    public ResponseEntity<StudentUpdateDTO> updateStudent(@RequestBody StudentUpdateDTO studentUpdateDTO) {
        studentService.updateStudent(studentUpdateDTO);
        return new ResponseEntity<>(studentUpdateDTO, HttpStatus.OK);
    }

    @GetMapping("/api/teacher/student/find-delete-student/{id}")
    public ResponseEntity<StudentDeleteDTO> getStudentFullById(@PathVariable int id) {
        StudentDeleteDTO studentDeleteDTO = this.studentService.getStudentFullById(id);
        if (studentDeleteDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(studentDeleteDTO, HttpStatus.OK);
    }


    @DeleteMapping("/api/teacher/student/delete-student/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable int id) {
        this.studentService.deleteStudent(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/api/student/student/search-student")
    public ResponseEntity<List<StudentListDTO>> searchStudentByNameAndHometown(@RequestParam int index,
                                                                               @RequestParam String name,
                                                                               @RequestParam String hometown) {
        List<StudentListDTO> searchStudent = this.studentService.searchStudentByNameAndHometown(index, name, hometown);
        if (searchStudent.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(searchStudent, HttpStatus.OK);
    }

    @GetMapping("/api/student/student/lists-student")
    public ResponseEntity<List<StudentListDTO>> getAllStudentByClassId(@RequestParam int classId,
                                                                       @RequestParam int index) {
        if (classId != 0) {
            List<StudentListDTO> listStudentByClassId = this.studentService.getAllStudentByClassId(classId, index);
            if (listStudentByClassId.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(listStudentByClassId, HttpStatus.OK);

        } else {
            int schoolYearId = this.schoolYearService.getCurrentYearId();
            List<StudentListDTO> listStudent = this.studentService.getAllStudentCurrentYear(schoolYearId, index);
            if (listStudent.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(listStudent, HttpStatus.OK);
        }
    }

    @GetMapping("/api/student/student/list-student")
    public ResponseEntity<List<StudentListDTO>> getAllStudent(@RequestParam int classId) {
        if (classId != 0) {
            List<StudentListDTO> studentListDTOS = this.studentService.getAllStudent(classId);
            if (studentListDTOS.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(studentListDTOS, HttpStatus.OK);
        } else {
            int schoolYearId = this.schoolYearService.getCurrentYearId();
            List<StudentListDTO> studentListDTOS = this.studentService.getAllStudentPage(schoolYearId);
            if (studentListDTOS.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(studentListDTOS, HttpStatus.OK);
        }
    }
    @PostMapping("/api/admin/student/create-student")
    @Transactional
    public ResponseEntity<?> createTeacher(@RequestBody List<CreateStudentDTO> studentDTOList, @RequestParam int teacherId, @RequestParam int classId) {
        for (CreateStudentDTO student : studentDTOList) {
            String defaultPassword = "123456";
            this.studentService.createStudentDTO(student);
            Integer studentId = studentService.getLatestId();
            System.out.println(studentId);
            String userName = "HS" + String.format("%05d", studentId);
            accountService.createAccount(userName, encoder.encode(defaultPassword), 1);
            int accountId = accountService.findIdByUsername(userName);
            accountRoleService.createAccountRole(accountId, 3);
            studentService.setAccountId(accountId, studentId);
            reportCardService.createReportCard(classId, studentId);
        }
        if (teacherId != 0) {
            this.studentClassService.updateTeacher(teacherId, classId);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
