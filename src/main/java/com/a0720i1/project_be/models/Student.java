package com.a0720i1.project_be.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthday;
    private String gender;
    private String hometown;
    private String ethnicity;
    private String religion;
    private String imageUrl;
    private String email;

    @OneToMany(mappedBy = "student")
    private List<ReportCard> reportCardList;

    @OneToOne
    private Account account;

}
