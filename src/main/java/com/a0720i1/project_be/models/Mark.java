package com.a0720i1.project_be.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Mark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Double markCol1;
    private Double markCol2;
    private Double markCol3;
    private int multiplier;
    @ManyToOne
    @JoinColumn(name = "subject_result_id", referencedColumnName = "id")
    private SubjectResult subjectResult;
}
