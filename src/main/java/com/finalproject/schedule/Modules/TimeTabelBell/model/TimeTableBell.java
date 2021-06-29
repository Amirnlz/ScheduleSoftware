package com.finalproject.schedule.Modules.TimeTabelBell.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.finalproject.schedule.Modules.Announcements.model.Announce;
import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Course.model.Course;
import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.Master.model.TimeTable;
import com.finalproject.schedule.Modules.User.model.User;
import io.swagger.annotations.BasicAuthDefinition;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;


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



  /*  @ManyToMany(mappedBy = "timetablebell")
    @JsonIgnore
    private List<TimeTable> timetable;*/

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



/* public void setTimetable(List<TimeTable> timetable) {
        this.timetable = timetable;
    }

    public List<TimeTable> getTimetable() {
        return timetable;
    }*/

}
