package com.a0720i1.project_be.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    private Subject subject;

    @OneToOne
    private Teacher teacher;

    @OneToOne
    private LessonTime lessonTime;

    @ManyToOne
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    private Schedule schedule;
}
