package com.a0720i1.project_be.repositories;

import com.a0720i1.project_be.dto.class_student.ClassStudentListDTO;
import com.a0720i1.project_be.dto.class_student.ClassStudentNameDTO;
import com.a0720i1.project_be.dto.class_student.ClassStudentTeacherListDTO;
import com.a0720i1.project_be.dto.teacher.TeacherListDTO;
import com.a0720i1.project_be.models.SchoolYear;
import com.a0720i1.project_be.models.StudentClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentClassRepository extends JpaRepository<StudentClass , Integer> {


    @Query(value = "Select teacher.id, teacher.name from teacher "
            , nativeQuery = true)
    List<ClassStudentTeacherListDTO> getAllTeacher();

    @Query(value = "Select student_class.id ,  student_class.name from student_class; "
            , nativeQuery = true)
    List<ClassStudentNameDTO> getAllClassName();

    @Query(value = "select student.id , student.name , student.birthday , student.hometown , student.gender from student;"
            , nativeQuery = true)
    List<ClassStudentListDTO> getAllStudent();


}
