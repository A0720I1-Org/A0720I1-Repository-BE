package com.a0720i1.project_be.services;

import com.a0720i1.project_be.dto.StudentResultDTO;
import com.a0720i1.project_be.dto.class_student.ClassListDTO;
import com.a0720i1.project_be.dto.class_student.StudentAverageMarkDTO;
import com.a0720i1.project_be.dto.class_student.StudentListDTO;
import com.a0720i1.project_be.dto.class_student.StudentResultUpdateDTO;
import com.a0720i1.project_be.models.Mark;
import com.a0720i1.project_be.models.ReportCard;
import com.a0720i1.project_be.models.SchoolYear;
import com.a0720i1.project_be.models.Subject;

import java.util.List;

public interface SubjectResultService {
    List<ClassListDTO> findAllClass();
    List<Subject> findAllSubject();
    List<StudentResultDTO> findStudentResult(int semesterId,int stuClaId,int subId);
    void updateMark(int semesterId,int stuClaId,int subId, StudentResultUpdateDTO studentResultDTO);
    Integer getMarkId(int semesterId,int stuClaId,int subId, StudentResultUpdateDTO studentResultDTO);
    int getSubjectResultId(int semId,int subId);
    int getSemesterResultId(int reportCardId,int semester) ;
    List<StudentListDTO> getListStudent(int stuClaId);
    List<Mark> getListMark(int subReId);
    ReportCard getReportCard(int stuClaId,int stuId);
    List<StudentResultUpdateDTO> getListStudentResult(int semesterId,int stuClaId,int subId);
    SchoolYear getCurrentSchoolYear();
    List<StudentAverageMarkDTO> getStudentAverageMark(int semesterId,int stuClaId);
}
