package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.dto.schedule.LessonDTO;
import com.a0720i1.project_be.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    @Modifying
    @Query(value = "select lesson_time.id, teacher.name as nameTeacher, subject.name as nameSubject, lesson_date as lessonDate, lesson_number as lessonNumber " +
            "from lesson " +
            "left join lesson_time on lesson.lesson_time_id = lesson_time.id " +
            "left join schedule on lesson.schedule_id = schedule.id " +
            "left join teacher on lesson.teacher_id = teacher.id " +
            "left join subject on lesson.subject_id = subject.id " +
            "where schedule.student_class_id = ?1", nativeQuery = true)
    List<LessonDTO> getAllLesson(int studentClassId);
}
