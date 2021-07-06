package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.dto.StudentResultDTO;
import com.a0720i1.project_be.dto.class_student.*;
import com.a0720i1.project_be.models.*;
import com.a0720i1.project_be.repositories.*;
import com.a0720i1.project_be.services.SemesterResultService;
import com.a0720i1.project_be.services.SubjectResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubjectResultServiceImpl implements SubjectResultService {
    @Autowired
    SubjectResultRepository subjectResultRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    MarkRepostitory markRepostitory ;
    @Autowired
    ReportCardRepository reportCardRepository ;
    @Autowired
    SemesterResultRepository semesterResultRepository ;
    @Autowired
    SchoolYearRepository schoolYearRepository ;
    @Override
    public List<ClassListDTO> findAllClass() {
        SchoolYear schoolYear = this.getCurrentSchoolYear();
        return subjectResultRepository.findAllClass(schoolYear.getId());
    }

    @Override
    public List<Subject> findAllSubject() {
        return subjectRepository.findAll();
    }

    @Override
    public List<StudentResultDTO> findStudentResult(int semesterId,int stuClaId,int subId) {
        return subjectResultRepository.findStudentResult(semesterId,stuClaId,subId);
    }

    @Override
    public void updateMark(int semesterId,int stuClaId,int subId, StudentResultUpdateDTO studentResultDTO) {
        Integer markId = this.getMarkId(semesterId,stuClaId,subId,studentResultDTO);
        if(studentResultDTO.getMultiplier() == 3) {
            studentResultDTO.setMarkCol2(null);
            studentResultDTO.setMarkCol3(null);
        }
        subjectResultRepository.changeMark(studentResultDTO.getMarkCol1(),studentResultDTO.getMarkCol2(),studentResultDTO.getMarkCol3(),markId);
    }

    @Override
    public Integer getMarkId(int stuClaId, int semesterId, int subId, StudentResultUpdateDTO studentResultDTO) {
        return subjectResultRepository.getMarkId(semesterId,stuClaId,subId,studentResultDTO.getStudentId(),studentResultDTO.getMultiplier());
    }

    @Override
    public int getSubjectResultId(int semId, int subId) {
        if(subjectResultRepository.getBySemesterResultIdAndSubjectId(semId,subId) == null) {
            subjectResultRepository.saveSubjectResult(semId,subId);
        }
        return subjectResultRepository.getBySemesterResultIdAndSubjectId(semId,subId).getId() ;
    }

    @Override
    public int getSemesterResultId(int reportCardId, int semester) {
        if(semesterResultRepository.getByReportCardIdAndSemester(reportCardId,semester) == null) {
            subjectResultRepository.saveSemesterResult(reportCardId);
        }
        return semesterResultRepository.getByReportCardIdAndSemester(reportCardId,semester).getId();
    }

    @Override
    public List<StudentListDTO> getListStudent(int stuClaId) {
        return subjectResultRepository.getStudentList(stuClaId);
    }

    @Override
    public List<Mark> getListMark(int subReId) {
        return markRepostitory.findMarkBySubjectResultId(subReId);
    }

    @Override
    public ReportCard getReportCard(int stuClaId, int stuId) {
        return reportCardRepository.findByStudentClassIdAndStudentId(stuClaId,stuId);
    }

    @Override
    public List<StudentResultUpdateDTO> getListStudentResult(int semesterId, int stuClaId, int subId) {
        List<StudentResultUpdateDTO> studentResultUpdateDTOS = new ArrayList<>();
        List<StudentListDTO> studentListDTOS = this.getListStudent(stuClaId);
        for (StudentListDTO student : studentListDTOS) {
            ReportCard reportCard = this.getReportCard(stuClaId,student.getId());
            int semesterResultId = this.getSemesterResultId(reportCard.getId(),semesterId);
            int subjectResultId = this.getSubjectResultId(semesterResultId,subId);
            List<Mark> markList = this.getListMark(subjectResultId);
            for (Mark mark : markList) {
                StudentResultUpdateDTO studentResultUpdateDTO = new StudentResultUpdateDTO();
                studentResultUpdateDTO.setName(student.getName());
                studentResultUpdateDTO.setStudentId(student.getId());
                studentResultUpdateDTO.setBirthday(student.getBirthday());
                studentResultUpdateDTO.setMarkCol1(mark.getMarkCol1());
                studentResultUpdateDTO.setMarkCol2(mark.getMarkCol2());
                studentResultUpdateDTO.setMarkCol3(mark.getMarkCol3());
                studentResultUpdateDTO.setMultiplier(mark.getMultiplier());
                studentResultUpdateDTOS.add(studentResultUpdateDTO);
            }
        }
        return  studentResultUpdateDTOS ;
    }

    @Override
    public SchoolYear getCurrentSchoolYear() {
        Date date = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int month = calendar.get(Calendar.MONTH) + 1;
        if(month > 9) {
            int beginYear = calendar.get(Calendar.YEAR);;
            int endYear = calendar.get(Calendar.YEAR) + 1;
            return schoolYearRepository.getSchoolYearByBeginYearAndEndYear(beginYear,endYear);
        }
        else return schoolYearRepository.getSchoolYearByBeginYearAndEndYear(calendar.get(Calendar.YEAR)-1,calendar.get(Calendar.YEAR));
    }

    @Override
    public StudentAverageMarkDTO getStudentAverageMark(int semesterId, int stuClaId, int subId, int studentId) {
        int count =  0 ;
        double sum = 0 ;
        double average = 1 ;
        String subjectName = null ;
        StudentAverageMarkDTO studentAverageMarkDTO = new StudentAverageMarkDTO();
        List<MarkDTO> listMark = subjectResultRepository.getListMarkStudent(semesterId,stuClaId, subId,studentId);
        for (MarkDTO markDTO: listMark) {
            if(markDTO.getMarkCol1() != null) {
                count += markDTO.getMultiplier();
                sum += markDTO.getMarkCol1() * markDTO.getMultiplier();
            }
            if(markDTO.getMarkCol2() != null) {
                count += markDTO.getMultiplier();
                sum += markDTO.getMarkCol2() * markDTO.getMultiplier();
            }
            if(markDTO.getMarkCol3() != null) {
                count += markDTO.getMultiplier();
                sum += markDTO.getMarkCol3() * markDTO.getMultiplier();
            }
        }
        subjectName = listMark.get(1).getSubjectName();
        if(count == 0) {
            studentAverageMarkDTO.setSubjectName(subjectName);
            studentAverageMarkDTO.setAverageMark(null);
            return studentAverageMarkDTO;
        }
        average = sum/count ;
        studentAverageMarkDTO.setAverageMark(average);
        studentAverageMarkDTO.setSubjectName(subjectName);
        return studentAverageMarkDTO ;
    }

}
