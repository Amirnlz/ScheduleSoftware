package com.finalproject.schedule.Modules.Master.model;

import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Course.model.Course;
import com.finalproject.schedule.Modules.Day.model.Day;

import javax.persistence.Entity;



public class TimeTableBellForFront {

    private Bell bell;

    private Day day;


    public Bell getBell() {
        return bell;
    }

    public void setBell(Bell bell) {
        this.bell = bell;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }
}
