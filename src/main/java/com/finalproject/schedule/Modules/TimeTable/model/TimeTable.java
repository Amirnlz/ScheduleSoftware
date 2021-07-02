package com.finalproject.schedule.Modules.TimeTable.model;

import com.finalproject.schedule.Modules.Course.model.Course;
import com.finalproject.schedule.Modules.TimeTableBell.model.TimeTableBell;
import com.finalproject.schedule.Modules.User.model.User;

import javax.persistence.*;

@Entity
@Table(name = "timetable_tbl")
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn
    @OneToOne(cascade= {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
    private User user;

    @JoinColumn
    @OneToOne(cascade= {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
    private TimeTableBell timetablebell;

    @JoinColumn
    @OneToOne
    private Course course;

    public TimeTable() {
    }

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

    public TimeTableBell getTimetablebell() {
        return timetablebell;
    }

    public void setTimetablebell(TimeTableBell timetablebell) {
        this.timetablebell = timetablebell;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

}
