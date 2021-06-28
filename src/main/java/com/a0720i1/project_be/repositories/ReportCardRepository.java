package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.models.ReportCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReportCardRepository extends JpaRepository<ReportCard,Integer> {
    ReportCard findByStudentClassIdAndStudentId(int stuClaId,int stuId) ;
}
