package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.dto.schedule.StudentClassDTO;
import com.a0720i1.project_be.models.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface StudentClassRepository extends JpaRepository<StudentClass, Integer> {
    @Transactional
    @Modifying
    @Query(value = "select student_class.id as Id, student_class.name as studentClassName, teacher.name as teacherName, school_year.id as schoolYearId, grade.id as gradeId, grade.name as gradeName, school_year.begin_year as beginYear, school_year.end_year as endYear\n" +
            "from student_class\n" +
            "left join grade on student_class.grade_id = grade.id\n" +
            "left join school_year on student_class.school_year_id = school_year.id\n" +
            "left join teacher on student_class.teacher_id = teacher.id\n" +
            "where grade.id = ?1 and school_year.id =?2 ", nativeQuery = true)
    List<StudentClassDTO> getStudentClass(int gradeId, int schoolYearId);
}
