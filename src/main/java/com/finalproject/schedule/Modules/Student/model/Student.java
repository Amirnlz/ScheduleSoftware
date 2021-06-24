package com.finalproject.schedule.Modules.Student.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;

@Entity
@Data
@Table(name = "student_tbl")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "codemelli")
public class Student {

    @Id
    private String codemelli;

    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private  int id;
    private String password;
    private String name;
    private String lastname;
    private String birthday;
    @Transient
    @JsonIgnore
    private MultipartFile file;
    private String cover;
    private boolean enabled = true;

}
