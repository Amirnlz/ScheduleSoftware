package com.finalproject.schedule.Modules.TimeTabelBell.service;

import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.Day.repository.DayRepository;
import com.finalproject.schedule.Modules.TimeTabelBell.model.TimeTableBell;
import com.finalproject.schedule.Modules.TimeTabelBell.repository.TimeTableBellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TimeTableBellService {

    private TimeTableBellRepository timetablebellRepository;

    @Autowired
    public TimeTableBellService(TimeTableBellRepository timetablebellRepository) {
        this.timetablebellRepository = timetablebellRepository;
    }

    @Transactional
    public TimeTableBell addTimeTableBell(TimeTableBell timetablebell) {
        return this.timetablebellRepository.save(timetablebell);
    }

    public List<TimeTableBell> findAllTimeTableBell() {
        return this.timetablebellRepository.findAll();
    }

}
