package com.finalproject.schedule.Modules.Bell.model;

import com.finalproject.schedule.Modules.Day.model.Day;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bell_tbl")
public class Bell {

    @Id
    @Column(unique = true)
    private int bellOfDay;
    @Column(unique = true,name = "label")
    private String Label;

    @ManyToMany(mappedBy = "bell")
    private List<Day> day;

    public Bell() {
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
