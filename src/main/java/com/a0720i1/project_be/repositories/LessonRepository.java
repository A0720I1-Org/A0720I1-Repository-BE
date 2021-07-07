package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.models.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    @Modifying
    @Query(value = "delete from lesson where schedule_id = ?1", nativeQuery = true)
    void deleteAllLessonByScheduleId(int id);

    @Modifying
    @Query(value = "insert into lesson(lesson_time_id, schedule_id, subject_id, teacher_id) values(?1, ?2, ?3, ?4)", nativeQuery = true)
    void insertNewLesson(int lessonTimeId, int scheduleId, int subjectId, int teacherId);
}
