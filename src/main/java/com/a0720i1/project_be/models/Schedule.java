package com.a0720i1.project_be.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonBackReference
    @OneToOne
    private StudentClass studentClass;
    @JsonBackReference
    @OneToMany (mappedBy = "schedule")
    private List<Lesson> lessonList;
}
