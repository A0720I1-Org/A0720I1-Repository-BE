package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.dto.schedule.TeacherDTO;
import com.a0720i1.project_be.dto.teacher.TeacherListDTO;
import com.a0720i1.project_be.dto.teacher.TeacherViewDTO;
import com.a0720i1.project_be.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    @Query(value = "select teacher.id, teacher.name,teacher.address, teacher.birthday, teacher.phone from teacher " +
            "left join account on teacher.account_id = account.id\n" +
            "left join account_role on account_role.account_id = account.id\n" +
            "left join role on role.id = account_role.role_id\n" +
            "where role.id = 2 " +
            "limit ?1,5", nativeQuery = true)
    List<TeacherListDTO> getPageAllTeacher(int index);

    @Query(value = "select teacher.id, teacher.name,teacher.address, teacher.birthday, teacher.phone from teacher " +
            "left join account on teacher.account_id = account.id\n" +
            "left join account_role on account_role.account_id = account.id\n" +
            "left join role on role.id = account_role.role_id\n" +
            "where role.id = 2", nativeQuery = true)
    List<TeacherListDTO> getListTeacher();

    @Query(value = "select t.id, t.address, t.birthday, t.email, t.gender, t.hometown, t.name, t.phone, t.level, t.position, t.image_url, student_class.name as studentClass \n" +
            "from teacher as t\n" +
            "left join student_class on student_class.teacher_id = t.id\n" +
            "where t.id = ?1", nativeQuery = true)
    TeacherViewDTO getTeacherById(int id);

    @Transactional
    @Modifying
    @Query(value = "update teacher as t set t.address = ?1, t.birthday = ?2, t.email = ?3, t.gender = ?4, t.hometown = ?5, " +
            "t.name = ?6, t.phone = ?7, t.level = ?8, t.position = ?9 " +
            "where t.id = ?10", nativeQuery = true)
    void updateTeacherDTO(String address, LocalDate birthday, String email, String gender, String hometown, String name,
                          String phone, String level, String position, int id);

    @Query(value = "select teacher.id, teacher.name, teacher.address, teacher.birthday, teacher.phone from teacher \n" +
            "left join account on teacher.account_id = account.id\n" +
            "left join account_role on account_role.account_id = account.id\n" +
            "left join role on role.id = account_role.role_id\n" +
            "where teacher.name like %?2% and teacher.address like %?3% \n" +
            "limit ?1,5", nativeQuery = true)
    List<TeacherListDTO> searchTeacherByNameAndAddress(int index, String name, String address);
    @Transactional
    @Modifying
    @Query(value = "select distinct subject.name as nameSubject, teacher.name as nameTeacher\n" +
            "from lesson\n" +
            "left join schedule on lesson.schedule_id = schedule.id\n" +
            "left join teacher on lesson.teacher_id = teacher.id\n" +
            "join subject on lesson.subject_id = subject.id\n" +
            "where schedule.student_class_id = ?1",nativeQuery = true)
    List<TeacherDTO> getTeacherForSubject(int studentClassId);
}
