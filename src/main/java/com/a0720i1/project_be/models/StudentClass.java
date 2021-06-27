package com.a0720i1.project_be.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class StudentClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "grade_id", referencedColumnName = "id")
    private Grade grade;
    @ManyToOne
    @JoinColumn(name = "school_year_id", referencedColumnName = "id")
    private SchoolYear schoolYear;
    private String name;
    @JsonBackReference
    @OneToMany (mappedBy = "studentClass")
    private List<ReportCard> reportCardList;
    @OneToOne(mappedBy = "studentClass")
    private Schedule schedule;
    @OneToOne
    private Teacher teacher;
}