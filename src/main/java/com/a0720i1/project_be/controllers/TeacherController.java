package com.a0720i1.project_be.controllers;

import com.a0720i1.project_be.dto.teacher.TeacherListDTO;
import com.a0720i1.project_be.dto.teacher.TeacherUpdateDTO;
import com.a0720i1.project_be.dto.teacher.TeacherViewDTO;
import com.a0720i1.project_be.services.impl.TeacherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    private TeacherServiceImpl teacherService;

    @GetMapping("")
    public ResponseEntity<List<TeacherListDTO>> getPageAllTeacher(int index) {
        List<TeacherListDTO> teacherPageListDTOList = this.teacherService.getPageAllTeacher(index);
        if (teacherPageListDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(teacherPageListDTOList, HttpStatus.OK);
    }

    @GetMapping("/lists")
    public ResponseEntity<List<TeacherListDTO>> getListTeacher() {
        List<TeacherListDTO> teacherListDTOList = this.teacherService.getListTeacher();
        if (teacherListDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(teacherListDTOList, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<TeacherViewDTO> getTeacherById(@PathVariable int id) {
        TeacherViewDTO teacherViewDTO = this.teacherService.getTeacherById(id);
        if (teacherViewDTO == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(teacherViewDTO, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<TeacherUpdateDTO> updateTeacher(@RequestBody TeacherUpdateDTO teacherUpdateDTO) {
        this.teacherService.updateTeacher(teacherUpdateDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<TeacherListDTO>> searchTeacherByNameAndAddress(@RequestParam int index,
                                                                              @RequestParam String name,
                                                                              @RequestParam String address) {
        List<TeacherListDTO> teacherListDTOList = this.teacherService.searchTeacherByNameAndAddress(index, name, address);
        if (teacherListDTOList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(teacherListDTOList, HttpStatus.OK);
    }

}
