package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.models.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentClassRepository extends JpaRepository<StudentClass,Integer> {
    @Query(value = "select * from student_class where school_year_id = ?1 and grade_id = ?2 order by name", nativeQuery = true)
    List<StudentClass> findByYearIdAndGradeId(int yearId, int gradeId);
}

