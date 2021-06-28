package com.a0720i1.project_be.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class ReportCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "student_class_id", referencedColumnName = "id")
    private StudentClass studentClass;

    @JsonBackReference
    @OneToMany(mappedBy = "reportCard")
    private List<SemesterResult> semesterResultList;
}
