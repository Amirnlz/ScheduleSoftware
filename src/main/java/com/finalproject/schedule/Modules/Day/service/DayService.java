package com.finalproject.schedule.Modules.Day.service;

import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.Day.repository.DayRepository;
import com.finalproject.schedule.Modules.TimeTableBell.model.TimeTableBell;
import com.finalproject.schedule.Modules.TimeTableBell.repository.TimeTableBellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DayService {

    private DayRepository dayRepository;
    private TimeTableBellRepository timetablebellRepository;

    @Autowired
    public DayService(DayRepository dayRepository, TimeTableBellRepository timetablebellRepository) {
        this.dayRepository = dayRepository;
        this.timetablebellRepository = timetablebellRepository;
    }

    @Transactional
    public Day addDay(Day day) {
        return this.dayRepository.save(day);
    }

    public List<Day> findAllDays() {
        return this.dayRepository.findAll();
    }

    public  Day findById(int id){
        return  dayRepository.findById(id);
    }

    public void deleteById(int id){
        for (TimeTableBell timetablebell:timetablebellRepository.findAll()){
            if(timetablebell.getDay().getId() == id){
                timetablebellRepository.deleteById(timetablebell.getId());
            }
        }
        dayRepository.deleteById(id);
    }

}

