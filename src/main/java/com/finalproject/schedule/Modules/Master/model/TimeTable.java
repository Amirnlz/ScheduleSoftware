package com.finalproject.schedule.Modules.Master.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finalproject.schedule.Modules.Course.model.Course;
import com.finalproject.schedule.Modules.TimeTabelBell.model.TimeTableBell;
import com.finalproject.schedule.Modules.User.model.User;

import javax.persistence.*;
import java.util.List;

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

    public void setId(int id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setCourse(String course) {
        Course = course;
    }

    public void setTimetablebell(List<TimeTableBell> timetablebell) {
        this.timetablebell = timetablebell;
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getCourse() {
        return Course;
    }

    public List<TimeTableBell> getTimetablebell() {
        return timetablebell;
    }

    @ManyToMany
    @JoinTable(name = "timetable_timetablebell")
    private List<TimeTableBell> timetablebell;

}
