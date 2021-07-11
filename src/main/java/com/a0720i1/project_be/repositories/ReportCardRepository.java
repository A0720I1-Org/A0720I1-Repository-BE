package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.models.ReportCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReportCardRepository extends JpaRepository<ReportCard,Integer> {
    ReportCard findByStudentClassIdAndStudentId(int stuClaId,int stuId);
    @Modifying
    @Query(value = "insert into report_card(student_class_id, student_id) values (?1, ?2)", nativeQuery =  true)
    void createReportCard(int classId, int studentId);
}
