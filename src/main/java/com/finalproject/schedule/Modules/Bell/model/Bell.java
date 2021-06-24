package com.finalproject.schedule.Modules.Bell.model;

import com.finalproject.schedule.Modules.Day.model.Day;

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

    @ManyToMany(mappedBy = "bell")
    private List<Day> day;

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

    public void setDay(List<Day> day) {
        this.day = day;
    }

    public List<Day> getDay() {
        return day;
    }
}
