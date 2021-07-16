package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.a0720i1.project_be.dto.teacher.TeacherScheduleDTO;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    @Modifying
    @Query(value = "delete from lesson where schedule_id = ?1", nativeQuery = true)
    void deleteAllLessonByScheduleId(int id);

    @Modifying
    @Query(value = "insert into lesson(lesson_time_id, schedule_id, subject_id, teacher_id) values(?1, ?2, ?3, ?4)", nativeQuery = true)
    void insertNewLesson(int lessonTimeId, int scheduleId, int subjectId, int teacherId);

    //Duy
    @Query(value = "select subject.name as subjectName, lesson_time.lesson_date as lessonDate, lesson_time.lesson_number as lessonNumber, teacher.name as teacherName, student_class.name as nameClass from lesson_time\n" +
            "left join lesson on lesson_time.id = lesson.lesson_time_id\n" +
            "left join subject on lesson.subject_id = subject.id\n" +
            "left join teacher on teacher.id = lesson.teacher_id\n" +
            "left join schedule on schedule.id = lesson.schedule_id\n" +
            "left join student_class on student_class.id = schedule.student_class_id\n" +
            "left join account on account.id = teacher.account_id\n" +
            "where account.username = ?1", nativeQuery = true)
    List<TeacherScheduleDTO> getAllLessonByTeacherUsername(String username);
}
