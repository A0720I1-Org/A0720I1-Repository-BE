package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.dto.StudentResultDTO;
import com.a0720i1.project_be.dto.class_student.ClassListDTO;
import com.a0720i1.project_be.dto.class_student.StudentListDTO;
import com.a0720i1.project_be.models.Subject;
import com.a0720i1.project_be.models.SubjectResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface SubjectResultRepository extends JpaRepository<SubjectResult,Integer> {
//    PhatDT tìm subject-result
    SubjectResult getBySemesterResultIdAndSubjectId(int semesterResultId ,int subjectId);
    @Modifying
    @Query(value = "select student.id as studentId,mark.mark_col1 as markCol1 ,mark.mark_col2 as markCol2,mark.mark_col3 as markCol3,student.name,mark.multiplier,student.birthday from mark \n" +
            "join subject_result on mark.subject_result_id = subject_result.id\n" +
            "join subject on subject_result.subject_id = subject.id\n" +
            "join semester_result on subject_result.semester_result_id = semester_result.id\n" +
            "join report_card on semester_result.report_card_id = report_card.id\n" +
            "join student on report_card.student_id = student.id\n" +
            "where semester_result.semester =?1 and report_card.student_class_id = ?2 and subject.id = ?3 ",nativeQuery = true)
    List<StudentResultDTO> findStudentResult(int semId,int stuClaId,int subId);
    //    PhatDT tìm class-student
    @Modifying
    @Query(value = "select student_class.name as name ,student_class.id as id ,\n" +
            "school_year.begin_year as beginYear,school_year.end_year as endYear from student_class\n" +
            "join school_year on student_class.school_year_id = school_year.id\n" +
            "where school_year.id = 1",nativeQuery = true)
    List<ClassListDTO> findAllClass() ;
    //    PhatDT
    @Query(value = "select mark.id from mark\n" +
            "join subject_result on mark.subject_result_id = subject_result.id\n" +
            "join subject on subject_result.subject_id = subject.id\n" +
            "join semester_result on subject_result.semester_result_id = semester_result.id\n" +
            "join report_card on semester_result.report_card_id = report_card.id\n" +
            "join student on report_card.student_id = student.id\n" +
            "where  semester_result.semester = ?1 and report_card.student_class_id = ?2 and subject.id = ?3 and student.id = ?4 and mark.multiplier = ?5",nativeQuery = true)
    Integer getMarkId(int semId,int stuClaId,int subId ,int stuId,int multiplier) ;
    //    PhatDT
    @Transactional
    @Modifying
    @Query(value = "update mark \n" +
            "set mark.mark_col1 = ?1,mark.mark_col2 = ?2 ,mark.mark_col3 = ?3\n" +
            "where mark.id = ?4",nativeQuery = true)
    void changeMark(Double markCol1,Double markCol2,Double markCol3,int markId);
    //    PhatDT
    @Transactional
    @Modifying
    @Query(value = "insert into subject_result(semester_result_id,subject_id) value (?1,?2)",nativeQuery = true)
    void saveSubjectResult(int semId,int subId);
    //    PhatDT
    @Transactional
    @Modifying
    @Query(value = "insert into semester_result(report_card_id,semester) value (?1,1),(?1,2)",nativeQuery = true)
    void saveSemesterResult(int repcId);
    //    PhatDT
    @Modifying
    @Query(value = "select student.id , student.name , student.birthday from student \n" +
            "join report_card on report_card.student_id = student.id\n" +
            "where report_card.student_class_id = ?1",nativeQuery = true)
    List<StudentListDTO> getStudentList(int stuClaId);
}
