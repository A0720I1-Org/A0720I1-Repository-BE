package com.a0720i1.project_be.services;


import com.a0720i1.project_be.dto.HomeRoomClassDTO;
import com.a0720i1.project_be.dto.StudentHomeroomClassDTO;
import com.a0720i1.project_be.models.Student;

import java.util.List;

public interface StudentService {
    List<HomeRoomClassDTO> getStudentByTeacherUsername(String username);
    List<HomeRoomClassDTO> getPageStudentByTeacherUsername(int index, String username);
    StudentHomeroomClassDTO getInforStudent(int id);
    List<HomeRoomClassDTO> searchStudentByName(String name, int index);
}
