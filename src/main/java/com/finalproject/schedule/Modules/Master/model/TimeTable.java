package com.finalproject.schedule.Modules.Master.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finalproject.schedule.Modules.User.model.User;

import javax.persistence.*;

@Entity
@Table(name = "timetable_tbl")
public class TimeTable {

    @Id
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    private String Course;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCourse() {
        return Course;
    }

    public void setCourse(String course) {
        Course = course;
    }

}
