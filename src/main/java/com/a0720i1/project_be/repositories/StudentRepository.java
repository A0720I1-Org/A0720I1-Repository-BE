package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.dto.HomeRoomClassDTO;
import com.a0720i1.project_be.dto.StudentHomeroomClassDTO;
import com.a0720i1.project_be.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

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
    @Query(value = "SELECT student_class.id from student_class\n" +
            "left join teacher on teacher.id = student_class.teacher_id \n" +
            "left join account on teacher.account_id = account.id\n" +
            "where account.username= ?1", nativeQuery = true)
    Integer getIdClassByUsernameTeacher(String username);

    //Duy
    @Query(value = "select student.id, student.name,student.image_url as imageUrl, student.birthday,student.gender, student.hometown, student.religion, student.ethnicity, student.email from student \n" +
            "where student.id =?1", nativeQuery = true)
    StudentHomeroomClassDTO getInfo(int id);

    //Duy
    @Query(value = "select student.id, student.name,student.image_url as imageUrl, student.birthday,student.gender, student.hometown, student.religion, student.ethnicity, student.email, student_class.name as className from student \n" +
            "left join report_card on report_card.student_id = student.id\n"+
            "left join student_class on report_card.student_class_id = student_class.id\n"+
            "where student.name like %?1%\n" +
            "limit ?2,5", nativeQuery = true)
    List<HomeRoomClassDTO> searchStudentByName(String name, int index);
}
