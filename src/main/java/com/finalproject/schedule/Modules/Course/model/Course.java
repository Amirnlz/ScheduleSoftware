package com.finalproject.schedule.Modules.Course.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.finalproject.schedule.Modules.User.model.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "course_tbl")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "coursenumber")
public class Course {

    @Id
    @Column(name = "coursenumber")
    private int CourseNumber;

    @Column(name = "title")
    private String Title;
    @Column(name = "unitscount")
    private int UnitsCount;
    @Column(name = "term")
    private int Term;

//    @ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
//    @JoinTable(name = "course_master")
//    private List<User> user;

    public Course() {
    }

    public Course(String title, int unitsCount, int term) {
        this.Title = title;
        this.UnitsCount = unitsCount;
        this.Term = term;
    }

    public int getCourseNumber() {
        return CourseNumber;
    }

    public void setCourseNumber(int courseNumber) {
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

//    public List<User> getUser() {
//        return user;
//    }
//
//    public void setUser(List<User> user) {
//        this.user = user;
//    }

}

