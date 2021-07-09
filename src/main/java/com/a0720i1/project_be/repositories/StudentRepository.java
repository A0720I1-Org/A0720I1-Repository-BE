package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.dto.student.StudentClass;
import com.a0720i1.project_be.dto.student.StudentDeleteDTO;
import com.a0720i1.project_be.dto.student.StudentListDTO;
import com.a0720i1.project_be.dto.student.StudentViewDTO;
import com.a0720i1.project_be.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    @Query(value = "select student.id, student.name,student.email, student.birthday, student.hometown\n" +
            "from student\n" +
            "left join report_card on report_card.student_id = student.id\n" +
            "where report_card.student_class_id = ?1", nativeQuery = true)
    List<StudentListDTO> getAllStudent(int classId);


    @Query(value = "select student.id, student.name,student.email, student.birthday, student.hometown\n" +
            "from student\n" +
            "join report_card on report_card.student_id = student.id\n" +
            "join student_class on student_class.id = report_card.student_class_id\n" +
            "where student_class.id = ?1\n"+
            "limit ?2,5", nativeQuery = true)
    List<StudentListDTO> getAllStudentByClassId(int classId, int index);


    @Query(value = "select * from student\n" +
            "where student.id = ?1", nativeQuery = true)
    StudentViewDTO getStudentById(int id);

    @Modifying
    @Transactional
    @Query(value = "update student as s set s.name = ?1, s.birthday = ?2, s.gender = ?3, s.hometown = ?4, s.ethnicity = ?5, " +
            "s.religion = ?6, s.image_url = ?7, s.email = ?8 " +
            "where s.id = ?9", nativeQuery = true)
    void updateStudentDTO(
            String name,
            LocalDate birthday,
            String gender,
            String hometown,
            String ethnicity,
            String religion,
            String imageUrl,
            String email, int id);

    @Modifying
    @Transactional
    @Query(value = "delete from report_card\n" +
            "where report_card.student_id = ?1", nativeQuery = true)
    void deleteStudent(int id);

    @Query(value = "select s.id , s.name, s.birthday,s.hometown from student as s\n" +
            "left join report_card on report_card.student_id = s.id\n"+
            "where report_card.id = ?1", nativeQuery = true)
    StudentDeleteDTO getStudentFullById(int id);

    @Query(value = "select student.id, student.name, student.email, student.birthday, student.hometown from student\n" +
            "left join account on student.account_id = account.id\n" +
            "left join account_role on account_role.account_id = account.id\n" +
            "left join role on role.id = account_role.role_id\n" +
            "where student.name like %?2% and student.hometown like %?3% \n" +
            "limit ?1,5", nativeQuery = true)
    List<StudentListDTO> searchStudentByNameAndHometown(int index, String name, String hometown);



}
