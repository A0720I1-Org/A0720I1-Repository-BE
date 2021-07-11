package com.a0720i1.project_be.dto.class_student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentAverageMarkDTO {
    Double averageMark ;
    String subjectName ;
}
