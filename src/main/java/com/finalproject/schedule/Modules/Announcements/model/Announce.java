package com.finalproject.schedule.Modules.Announcements.model;

import com.finalproject.schedule.Modules.Day.model.Day;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "annouce_tbl")
@NoArgsConstructor
public class Announce {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private  String title;

    private String text;

    private  String added_by;

    private Date date;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAdded_by() {
        return added_by;
    }

    public void setAdded_by(String added_by) {
        this.added_by = added_by;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
