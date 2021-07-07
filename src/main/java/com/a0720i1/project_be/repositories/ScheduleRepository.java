package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.dto.schedule.AssignedTeacherDTO;
import com.a0720i1.project_be.dto.schedule.LessonShowDto;
import com.a0720i1.project_be.dto.schedule.TeacherSubjectDTO;
import com.a0720i1.project_be.models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    // Đôn: Get schedule by class id
    @Transactional
    @Query(value = "select schedule.student_class_id as studentClassId, schedule.id as scheduleId, lesson.id as lessonId, lesson_time.lesson_date as lessonDate, " +
            "lesson_time.lesson_number as lessonNumber, subject.id as subjectId, subject.name as subjectName, teacher.name as teacherName " +
            "from lesson_time left join lesson on lesson_time.id = lesson.lesson_time_id " +
            "left join subject on subject.id = lesson.subject_id " +
            "left join teacher on teacher.id = lesson.teacher_id " +
            "left join schedule on schedule.id = lesson.schedule_id " +
            "where schedule.student_class_id = ?1", nativeQuery = true)
    public List<LessonShowDto> getScheduleByClassId(int classId);

    //Đôn: Get list of subject with teacher by class id
    @Transactional
    @Query(value = "select Distinct subject.id as subjectId,  subject.name as subjectName, " +
            "teacher.id as teacherId, teacher.name as teacherName " +
            "from subject join lesson on lesson.subject_id = subject.id " +
            "left join teacher on teacher.id = lesson.teacher_id " +
            "left join schedule on schedule.id = lesson.schedule_id where schedule.student_class_id = ?1 order by subjectId", nativeQuery = true)
    public Set<TeacherSubjectDTO> getTeacherSubjectByStudentClassId(int classId);

    @Transactional
    @Query (value = "select id from schedule where student_class_id = ?1", nativeQuery = true)
    int getScheduleIdByStudentClass_Id(int studentClassId);

    @Modifying
    @Query (value = "insert into schedule(student_class_id) values ?1", nativeQuery = true)
    void createNewSchedule(int classId);

    @Query (value = "select teacher.id as teacherId, teacher.name as teacherName, lesson_time.lesson_date as lessonDate, " +
            "lesson_time.lesson_number as lessonNumber, student_class.name as className " +
            "from lesson left join lesson_time on lesson_time.id = lesson.lesson_time_id " +
            "left join teacher on teacher.id = lesson.teacher_id " +
            "left join schedule on lesson.schedule_id = schedule.id " +
            "left join student_class on student_class.id = schedule.student_class_id " +
            "left join school_year on school_year.id = student_class.school_year_id " +
            "where school_year.id = ?1 and teacher.id = ?2 and lesson_time.id = ?3", nativeQuery = true)
    List<AssignedTeacherDTO> getAssignedTeacherListOnLessonTime(int schoolYearId, int teacherId, int lessonTimeId);

}
