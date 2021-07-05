package com.finalproject.schedule.Modules.Announcements.model;

import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.TimeTable.model.TimeTable;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;
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

    private String message;

    @JoinColumn
    @OneToOne
    private TimeTable timeTable;
}
