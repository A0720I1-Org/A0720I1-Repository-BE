package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.models.SemesterResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterResultRepository extends JpaRepository<SemesterResult,Integer> {
    SemesterResult getByReportCardIdAndSemester(int reportCardId,int semester);
}
