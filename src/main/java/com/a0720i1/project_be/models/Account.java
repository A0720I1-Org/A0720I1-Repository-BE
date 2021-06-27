package com.a0720i1.project_be.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    @JsonBackReference
    @OneToMany(mappedBy = "account")
    private List<AccountRole> accountRoleList;
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private boolean isEnabled;
}
