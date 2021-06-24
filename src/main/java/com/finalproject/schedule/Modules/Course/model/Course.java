package com.finalproject.schedule.Modules.Course.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.finalproject.schedule.Modules.Master.model.Master;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course_tbl")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "coursenumber")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "coursenumber")
    private Long CourseNumber;

    @Column(name = "title")
    private String Title;
    @Column(name = "unitscount")
    private int UnitsCount;
    @Column(name = "term")
    private int Term;

    @ManyToMany
    @JoinTable(name = "course_master")
    private List<Master> master;

    public Course(){}

    public Course(String title, int unitsCount, int term) {
        this.Title = title;
        this.UnitsCount = unitsCount;
        this.Term = term;
    }

    public Long getCourseNumber() {
        return CourseNumber;
    }

    public void setCourseNumber(Long courseNumber) {
        CourseNumber = courseNumber;
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

    public int getTerm() {
        return Term;
    }

    public void setTerm(int term) {
        Term = term;
    }

    public List<Master> getMaster() {
        return master;
    }

    public void setMaster(List<Master> master) {
        this.master = master;
    }
}

