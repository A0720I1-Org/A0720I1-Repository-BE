package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.dto.HomeRoomClassDTO;
import com.a0720i1.project_be.dto.StudentHomeroomClassDTO;
import com.a0720i1.project_be.dto.student_class.ClassCreateStudentDTO;
import com.a0720i1.project_be.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    //Duy
    @Modifying
    @Query(value = "select student.id, student.name,student.image_url as imageUrl, student.birthday,student.gender, student.hometown, student.religion, student.ethnicity, student.email, student_class.name as className from student \n" +
            "left join report_card on report_card.student_id = student.id\n"+
            "left join student_class on report_card.student_class_id = student_class.id\n"+
            "left join teacher on teacher.id = student_class.teacher_id\n"+
            "left join account on account.id = teacher.account_id\n"+
            "where account.username = ?1", nativeQuery = true)
    List<HomeRoomClassDTO> getStudentByTeacherUsername(String username);

    //Duy
    @Query(value = "select student.id, student.name,student.image_url as imageUrl, student.birthday,student.gender, student.hometown, student.religion, student.ethnicity, student.email, student_class.name as className from student \n" +
            "left join report_card on report_card.student_id = student.id\n"+
            "left join student_class on report_card.student_class_id = student_class.id\n"+
            "left join teacher on teacher.id = student_class.teacher_id\n"+
            "left join account on account.id = teacher.account_id\n"+
            "where account.username = ?1\n"+
            "limit ?2,5", nativeQuery = true)
    List<HomeRoomClassDTO> getPageStudentByTeacherUsername(String username, int index);

    //Duy
    @Query(value = "select student.id, student.name,student.image_url as imageUrl, student.birthday,student.gender, student.hometown, student.religion, student.ethnicity, student.email from student \n" +
            "where student.id =?1", nativeQuery = true)
    StudentHomeroomClassDTO getInfoStudent(int id);

    //Duy
    @Query(value = "select student.id, student.name,student.image_url as imageUrl, student.birthday,student.gender, student.hometown, student.religion, student.ethnicity, student.email, student_class.name as className from student \n" +
            "left join report_card on report_card.student_id = student.id\n"+
            "left join student_class on report_card.student_class_id = student_class.id\n"+
            "where student.name like %?1%\n" +
            "limit ?2,5", nativeQuery = true)
    List<HomeRoomClassDTO> searchStudentByName(String name, int index);

    //Don
    @Query(value = "select student.id, student.name,student.image_url as imageUrl, student.birthday,student.gender, " +
            "student.hometown, student.religion, student.ethnicity, student.email " +
            "from student left join report_card on report_card.student_id = student.id "+
            "where report_card.student_class_id = ?1", nativeQuery = true)
    List<ClassCreateStudentDTO> getAllStudentByClassId(int classId);

    //Nam
    @Transactional
    @Modifying
    @Query(value = "insert into student(name, birthday, gender, hometown, email, religion , ethnicity, image_url) values (?1, ?2, ?3, ?4, ?5, ?6 , ?7, ?8)"
            , nativeQuery = true)
    void createStudent(String name, LocalDate birthday, String gender, String hometown , String email, String religion , String ethnicity, String imageUrl);

    @Query(value = "select id from student where student.email  = ?1 limit 1" , nativeQuery = true)
    Integer getIdByEmail(String email);

    @Modifying
    @Query(value = "update student set student.account_id = ?1 where student.id = ?2" , nativeQuery = true)
    void setAccountId(int accountId , int studentId);
}
