package com.finalproject.schedule.Modules.TimeTableBell.service;

import com.finalproject.schedule.Modules.TimeTable.model.TimeTable;
import com.finalproject.schedule.Modules.TimeTable.repository.TimeTableRepository;
import com.finalproject.schedule.Modules.TimeTableBell.model.TimeTableBell;
import com.finalproject.schedule.Modules.TimeTableBell.repository.TimeTableBellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeTableBellService {

    private TimeTableBellRepository timetablebellRepository;
    private TimeTableRepository timetableRepository;

    @Autowired
    public TimeTableBellService(TimeTableBellRepository timetablebellRepository, TimeTableRepository timetableRepository) {
        this.timetablebellRepository = timetablebellRepository;
        this.timetableRepository = timetableRepository;
    }

    public TimeTableBell addTimeTableBell(TimeTableBell timeTableBell){
       return this.timetablebellRepository.save(timeTableBell);
    }
    public List<TimeTableBell> findAllTimeTableBell(){
        return  this.timetablebellRepository.findAll();
    }

    public TimeTableBell findById(int id){
        return  timetablebellRepository.findById(id);
    }

    public void deleteById(int id){
        for (TimeTable timetable:timetableRepository.findAll()){
            if(timetable.getTimetablebell().getId() == id){
                timetableRepository.deleteById(timetable.getId());
            }
        }
        timetablebellRepository.deleteById(id);
    }



}
