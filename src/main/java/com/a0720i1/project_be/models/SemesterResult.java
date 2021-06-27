package com.a0720i1.project_be.models;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class SemesterResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int semester;
    @OneToMany (mappedBy = "semesterResult")
    private List<SubjectResult> subjectResultList;
    @ManyToOne
    @JoinColumn(name = "report_card_id", referencedColumnName = "id")
    private ReportCard reportCard;
}
