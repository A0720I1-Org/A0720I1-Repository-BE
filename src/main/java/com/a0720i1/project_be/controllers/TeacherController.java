package com.a0720i1.project_be.controllers;

import com.a0720i1.project_be.dto.HomeRoomClassDTO;
import com.a0720i1.project_be.dto.StudentHomeroomClassDTO;
import com.a0720i1.project_be.dto.teacher.TeacherListDTO;
import com.a0720i1.project_be.dto.teacher.TeacherScheduleDTO;
import com.a0720i1.project_be.dto.teacher.TeacherUpdateDTO;
import com.a0720i1.project_be.dto.teacher.TeacherViewDTO;
import com.a0720i1.project_be.services.impl.LessonServiceImpl;
import com.a0720i1.project_be.services.impl.StudentServiceImpl;
import com.a0720i1.project_be.services.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    private TeacherServiceImpl teacherService;

    @Autowired
    private StudentServiceImpl studentService;

    @Autowired
    private LessonServiceImpl lessonService;

    @GetMapping("api/teacher/")
    public ResponseEntity<List<TeacherListDTO>> getPageAllTeacher(int index) {
        List<TeacherListDTO> teacherPageListDTOList = this.teacherService.getPageAllTeacher(index);
        if (teacherPageListDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(teacherPageListDTOList, HttpStatus.OK);
    }

    @GetMapping("api/teacher/lists")
    public ResponseEntity<List<TeacherListDTO>> getListTeacher() {
        List<TeacherListDTO> teacherListDTOList = this.teacherService.getListTeacher();
        if (teacherListDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(teacherListDTOList, HttpStatus.OK);
    }

    @GetMapping("api/teacher/find/{id}")
    public ResponseEntity<TeacherViewDTO> getTeacherById(@PathVariable int id) {
        TeacherViewDTO teacherViewDTO = this.teacherService.getTeacherById(id);
        if (teacherViewDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(teacherViewDTO, HttpStatus.OK);
    }

    @PutMapping("api/teacher/update")
    public ResponseEntity<TeacherUpdateDTO> updateTeacher(@RequestBody TeacherUpdateDTO teacherUpdateDTO) {
        this.teacherService.updateTeacher(teacherUpdateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("api/teacher/search")
    public ResponseEntity<List<TeacherListDTO>> searchTeacherByNameAndAddress(@RequestParam int index,
                                                                              @RequestParam String name,
                                                                              @RequestParam String address) {
        List<TeacherListDTO> teacherListDTOList = this.teacherService.searchTeacherByNameAndAddress(index, name, address);
        if (teacherListDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(teacherListDTOList, HttpStatus.OK);
    }

    @GetMapping("api/teacher/teacher/homeroom-class/list/{username}")
    public ResponseEntity<List<HomeRoomClassDTO>> getStudentByClassId(@PathVariable String username){
        List<HomeRoomClassDTO> homeRoomClassDTOList = this.studentService.getStudentByTeacherUsername(username);
        if (homeRoomClassDTOList.isEmpty()){
            return  new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(homeRoomClassDTOList, HttpStatus.OK);
    }

    @GetMapping("api/teacher/teacher/homeroom-class/{username}")
    public ResponseEntity<List<HomeRoomClassDTO>> getPageStudentByClassId(@RequestParam int index,
                                                                          @PathVariable String username) {
        List<HomeRoomClassDTO> homeRoomClassDTOList = this.studentService.getPageStudentByTeacherUsername(index, username);
        if (homeRoomClassDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(homeRoomClassDTOList, HttpStatus.OK);
    }

    @GetMapping("api/teacher/teacher/homeroom-class/details/{id}")
    public ResponseEntity<StudentHomeroomClassDTO> getStudentById(@PathVariable int id){
        StudentHomeroomClassDTO student = this.studentService.getInforStudent(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping("api/teacher/teacher/homeroom-class/search")
    public ResponseEntity<List<HomeRoomClassDTO>> searchStudentByName(@RequestParam("index") int index,
                                                                      @RequestParam("name") String name) {
        List<HomeRoomClassDTO> homeRoomClassDTOList = this.studentService.searchStudentByName(name, index);
        if (homeRoomClassDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(homeRoomClassDTOList, HttpStatus.OK);
    }

    @GetMapping("api/teacher/teacher/teacher-schedule/{username}")
    public ResponseEntity<List<TeacherScheduleDTO>> getTeacherSchedule(@PathVariable String username){
        List<TeacherScheduleDTO> teacherScheduleDTOList = this.lessonService.getAllLessonByTeacherUsername(username);
        if (teacherScheduleDTOList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(teacherScheduleDTOList, HttpStatus.OK);
    }
}
