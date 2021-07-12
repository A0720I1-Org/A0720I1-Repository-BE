package com.a0720i1.project_be.common;

import com.a0720i1.project_be.dto.schedule.AssignedTeacherDTO;

import java.util.List;

public class AssignedTeacherException extends Exception{
    List<AssignedTeacherDTO> assignedTeacherDTOList;
    public AssignedTeacherException(String message, List<AssignedTeacherDTO> assignedTeacherDTOList) {
        super(message);
        this.assignedTeacherDTOList = assignedTeacherDTOList;
    }
}
