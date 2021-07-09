package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.dto.schedule.ScheduleSubjectDTO;
import com.a0720i1.project_be.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    @Query(value = "select subject.id as subjectId, subject.name as subjectName from subject", nativeQuery = true)
    List<ScheduleSubjectDTO> getAllSubject();
}
