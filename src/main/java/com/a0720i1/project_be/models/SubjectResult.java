package com.a0720i1.project_be.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class SubjectResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    private Subject subject;

    @ManyToOne
    @JoinColumn(name = "semester_result_id", referencedColumnName = "id")
    private SemesterResult semesterResult;

    @OneToMany (mappedBy = "subjectResult")
    private List<Mark> markList;
}
