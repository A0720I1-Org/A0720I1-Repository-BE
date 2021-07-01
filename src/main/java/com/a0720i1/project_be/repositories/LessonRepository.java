package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.dto.teacher.TeacherScheduleDTO;
import com.a0720i1.project_be.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {

    //Duy
    @Query(value = "select subject.name as subjectName, lesson_time.lesson_date as lessonDate, lesson_time.lesson_number as lessonNumber from lesson_time\n" +
            "left join lesson on lesson_time.id = lesson.lesson_time_id\n" +
            "left join subject on lesson.subject_id = subject.id\n" +
            "left join teacher on teacher.id = lesson.teacher_id\n" +
            "left join account on account.id = teacher.account_id\n" +
            "where account.username = ?1", nativeQuery = true)
    List<TeacherScheduleDTO> getAllLessonByTeacherUsername(String username);
}
