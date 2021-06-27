package com.finalproject.schedule.Modules.TimeTabelBell.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Course.model.Course;
import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.Master.model.TimeTable;
import com.finalproject.schedule.Modules.User.model.User;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;


public class TimeTableBell {


    private Day day;
    private Bell bell;

    @ManyToMany(mappedBy = "timetablebell")
    @JsonIgnore
    private List<TimeTable> timetable;

    public void setDay(Day day) {
        this.day = day;
    }

    public void setBell(Bell bell) {
        this.bell = bell;
    }

    public Day getDay() {
        return day;
    }

    public Bell getBell() {
        return bell;
    }

    public void setTimetable(List<TimeTable> timetable) {
        this.timetable = timetable;
    }

    public List<TimeTable> getTimetable() {
        return timetable;
    }

}
