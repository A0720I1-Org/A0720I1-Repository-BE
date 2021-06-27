package com.a0720i1.project_be.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthday;
    private String gender;
    private String email;
    private String address;
    private String hometown;
    private String phone;
    private String level;
    private String position;
    private String imageUrl;
    @JsonBackReference(value = "account")
    @OneToOne
    private Account account;
}
