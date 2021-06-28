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
    private  int id;

   // private Day day;
   // private Bell bell;

    private String dayLabel;
    private String bellLabel;
    private int dayOfWeek;
    private int bellOfDay;

  /*  @ManyToMany(mappedBy = "timetablebell")
    @JsonIgnore
    private List<TimeTable> timetable;*/

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDayLabel() {
        return dayLabel;
    }

    public void setDayLabel(String dayLabel) {
        this.dayLabel = dayLabel;
    }

    public String getBellLabel() {
        return bellLabel;
    }

    public void setBellLabel(String bellLabel) {
        this.bellLabel = bellLabel;
    }

    public int getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getBellOfDay() {
        return bellOfDay;
    }

    public void setBellOfDay(int bellOfDay) {
        this.bellOfDay = bellOfDay;
    }

/* public void setTimetable(List<TimeTable> timetable) {
        this.timetable = timetable;
    }

    public List<TimeTable> getTimetable() {
        return timetable;
    }*/

}
