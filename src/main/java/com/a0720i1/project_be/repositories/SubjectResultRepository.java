package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.dto.StudentResultDTO;
import com.a0720i1.project_be.models.SubjectResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectResultRepository extends JpaRepository<SubjectResult,Integer> {
    @Modifying
    @Query(value = "SELECT mark.mark_col1 as markCol1,mark.mark_col2 as markCol2,mark.mark_col3 as markCol3,mark.multiplier," +
            "student.name as studentName,student.id as studentId ,student.birthday as birthday FROM subject_result\n" +
            "left join mark on mark.subject_result_id = subject_result.id\n" +
            "left join subject on subject_result.subject_id =  subject.id\n" +
            "left join semester_result on subject_result.semester_result_id = semester_result.id\n" +
            "left join report_card on semester_result.report_card_id = report_card.id\n" +
            "left join student_class on report_card.student_class_id = student_class.id\n" +
            "left join teacher on student_class.teacher_id = teacher.id\n" +
            "left join student on report_card.student_id = student.id\n" +
            "where student_class.id = ?1 and subject.id = ?2 and semester_result.semester = ?3",nativeQuery = true)
    List<StudentResultDTO> findStudentResult(int stuClaId, int seReId, int subId);
    @Modifying
    @Query(value = "update subject_result\n" +
            "left join mark on mark.subject_result_id = subject_result.id\n" +
            "left join subject on subject_result.subject_id =  subject_id\n" +
            "left join semester_result on subject_result.semester_result_id = semester_result.id\n" +
            "left join report_card on semester_result.report_card_id = report_card.id\n" +
            "left join student_class on report_card.student_class_id = student_class.id\n" +
            "left join teacher on student_class.teacher_id = teacher.id\n" +
            "left join student on report_card.student_id = student.id\n" +
            "set  mark.mark_col1 = ?1 , mark.mark_col2 = ?2 ,mark.mark_col3 = ?3" +
            "where student_class.id = ?4 and subject.id = ?5 and semester_result.semester=?6 and student.id = ?7 and mark.multiplier=?8;",nativeQuery = true)
    void updateMark(Double col1, Double col2, Double col3, int stuClaId, int seReId, int subId, int stuId, int multiplier);
}
