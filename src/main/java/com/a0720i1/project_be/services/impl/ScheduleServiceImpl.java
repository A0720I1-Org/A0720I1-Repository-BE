package com.a0720i1.project_be.services.impl;

import com.a0720i1.project_be.dto.schedule.AssignedTeacherDTO;
import com.a0720i1.project_be.dto.schedule.LessonShowDto;
import com.a0720i1.project_be.dto.schedule.TeacherSubjectDTO;
import com.a0720i1.project_be.models.Schedule;
import com.a0720i1.project_be.repositories.ScheduleRepository;
import com.a0720i1.project_be.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    ScheduleRepository scheduleRepository;

    @Override
    public List<LessonShowDto> getScheduleByClassId(int classId) {
        return scheduleRepository.getScheduleByClassId(classId);
    }

    @Override
    public Set<TeacherSubjectDTO> getTeacherSubjectByStudentClassId(int classId) {
        return scheduleRepository.getTeacherSubjectByStudentClassId(classId);
    }

    @Override
    public int getScheduleIdByClassId(int classId) {
        return scheduleRepository.getScheduleIdByStudentClass_Id(classId);
    }

    @Override
    public void createNewSchedule(int classId) {
        this.scheduleRepository.createNewSchedule(classId);
    }

    @Override
    public List<AssignedTeacherDTO> getAllAssignedTeacherByLessonTimeId(int schoolYearId, int teacherId, int lessonTimeId) {
        return scheduleRepository.getAssignedTeacherListOnLessonTime(schoolYearId, teacherId, lessonTimeId);
    }

}
