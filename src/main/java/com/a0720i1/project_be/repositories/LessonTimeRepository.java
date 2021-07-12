package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.models.LessonTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonTimeRepository extends JpaRepository<LessonTime, Integer> {
    @Query (value = "select id from lesson_time where lesson_date = ?1 and lesson_number = ?2", nativeQuery = true)
    int findLessonTimeByLessonDateAndLessonNumber(int lessonDate, int lessonNumber);
}
