package com.a0720i1.project_be.services;

import com.a0720i1.project_be.dto.schedule.AssignedTeacherDTO;
import com.a0720i1.project_be.dto.schedule.LessonShowDto;
import com.a0720i1.project_be.dto.schedule.TeacherSubjectDTO;
import com.a0720i1.project_be.models.Schedule;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public interface ScheduleService {
    List<LessonShowDto> getScheduleByClassId(int classId);
    Set<TeacherSubjectDTO> getTeacherSubjectByStudentClassId(int classId);
    int getScheduleIdByClassId(int classId);
    void createNewSchedule(int classId);
    List<AssignedTeacherDTO> getAllAssignedTeacherByLessonTimeId(int schoolYearId, int teacherId, int lessonTimeId);
}
