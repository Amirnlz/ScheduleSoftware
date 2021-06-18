package com.finalproject.schedule.Modules.Course.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.finalproject.schedule.Modules.Master.model.Master;

import javax.persistence.*;

@Entity
@Table(name = "course_tbl")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Course {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "title")
    private String Title;
    @Column(name = "unitscount")
    private int UnitsCount;

    @ManyToOne
    @JoinColumn(name = "master_id")
    private Master master;

    public Course(){}

    public Course(String title, int unitsCount) {
        Title = title;
        UnitsCount = unitsCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getUnitsCount() {
        return UnitsCount;
    }

    public void setUnitsCount(int unitsCount) {
        UnitsCount = unitsCount;
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }
}

