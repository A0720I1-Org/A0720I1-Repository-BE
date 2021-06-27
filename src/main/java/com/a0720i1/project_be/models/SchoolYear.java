package com.a0720i1.project_be.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class SchoolYear {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int beginYear;
    private int endYear;
    @JsonBackReference
    @OneToMany (mappedBy = "schoolYear")
    private List<StudentClass> studentClassList;
}