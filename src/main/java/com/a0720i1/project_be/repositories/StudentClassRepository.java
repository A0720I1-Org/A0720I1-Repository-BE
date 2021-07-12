package com.a0720i1.project_be.repositories;


import com.a0720i1.project_be.dto.schedule.ScheduleClassDTO;
import com.a0720i1.project_be.models.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
public interface StudentClassRepository extends JpaRepository<StudentClass, Integer> {
    @Query(value = "select * from student_class where school_year_id = ?1 and grade_id = ?2 order by name", nativeQuery = true)
    List<StudentClass> findByYearIdAndGradeId(int yearId, int gradeId);

    @Query(value = "select student_class.id as classId, student_class.name as className from student_class where student_class.id = ?1", nativeQuery = true)
    ScheduleClassDTO findClassNameById(int id);
//    PhatDT
    @Query(value="select student_class.id as classId from student_class\n" +
            "left join school_year on school_year.id = student_class.school_year_id\n" +
            "left join report_card on report_card.student_class_id = student_class.id\n" +
            "left join student on report_card.student_id = student.id\n" +
            "where school_year.id = ?1 and student.id = ?2",nativeQuery = true)
    ScheduleClassDTO findClassByStudentIdAAndSchoolYearId(int schoolYearId,int studentId);
}

