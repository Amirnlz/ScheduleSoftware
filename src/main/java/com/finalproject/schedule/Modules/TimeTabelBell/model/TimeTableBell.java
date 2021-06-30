package com.finalproject.schedule.Modules.TimeTabelBell.model;

import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Day.model.Day;

import javax.persistence.*;


@Entity
@Table
public class TimeTableBell {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JoinColumn
    @OneToOne
    private Day day;

    @JoinColumn
    @OneToOne
    private Bell bell;


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
