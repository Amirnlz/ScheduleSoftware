package com.finalproject.schedule.Modules.Bell.model;

import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.TimeTabelBell.model.TimeTableBell;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bell_tbl")
public class Bell {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private int bellOfDay;
    @Column(unique = true,name = "label")
    private String Label;

    @OneToMany(mappedBy = "bell")
    private List<TimeTableBell> timetablebell;

    public Bell() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bell(int bellOfDay, String label) {
        this.bellOfDay = bellOfDay;
        this.Label = label;
    }

    public int getBellOfDay() {
        return bellOfDay;
    }

    public void setBellOfDay(int bellOfDay) {
        this.bellOfDay = bellOfDay;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

}
