package com.finalproject.schedule.Modules.TimeTableBell.model;

import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Day.model.Day;

import javax.persistence.*;


@Entity
@Table(name = "timetablebell_tbl")
public class TimeTableBell {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn
    @OneToOne(cascade= {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
    private Day day;

    @JoinColumn
    @OneToOne(cascade= {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
    private Bell bell;

    public TimeTableBell() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Bell getBell() {
        return bell;
    }

    public void setBell(Bell bell) {
        this.bell = bell;
    }


}
