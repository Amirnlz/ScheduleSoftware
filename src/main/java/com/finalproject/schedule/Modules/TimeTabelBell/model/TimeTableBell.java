package com.finalproject.schedule.Modules.TimeTabelBell.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Course.model.Course;
import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.Master.model.TimeTable;
import com.finalproject.schedule.Modules.User.model.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "timetablebell_tbl")
public class TimeTableBell {

    @Id
    private int timeTableId;

    @ManyToOne
    @JoinColumn(name = "dayId")
    @JsonIgnore
    private Day day;

    @ManyToOne
    @JoinColumn(name = "bellId")
    @JsonIgnore
    private Bell bell;

    public TimeTableBell() {
    }

    public void setTimeTableId(int timeTableId) {
        this.timeTableId = timeTableId;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public void setBell(Bell bell) {
        this.bell = bell;
    }

    public int getTimeTableId() {
        return timeTableId;
    }

    public Day getDay() {
        return day;
    }

    public Bell getBell() {
        return bell;
    }


}
