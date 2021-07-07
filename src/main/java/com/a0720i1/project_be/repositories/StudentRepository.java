package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.dto.class_student.ClassStudentTeacherListDTO;
import com.a0720i1.project_be.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student , Integer> {

    @Transactional
    @Modifying
    @Query(value = "insert into student(name, birthday, gender, hometown, email, religion , ethnicity) values (?1, ?2, ?3, ?4, ?5, ?6 , ?7)"
            , nativeQuery = true)
    void createStudentDTO(String name, LocalDate birthday, String gender, String hometown , String email, String religion , String ethnicity);

    @Query(value = "select id from student where student.email  = ?1 limit 1" , nativeQuery = true)
    Integer getIdByEmail(String email);

    @Modifying
    @Query(value = "update student set student.account_id = ?1 where student.id = ?2" , nativeQuery = true)
    void setAccountId(int accountId , int studentId);



}
