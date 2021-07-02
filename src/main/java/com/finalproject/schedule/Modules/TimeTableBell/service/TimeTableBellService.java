package com.finalproject.schedule.Modules.TimeTableBell.service;

import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.TimeTableBell.model.TimeTableBell;
import com.finalproject.schedule.Modules.TimeTableBell.repository.TimeTableBellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeTableBellService {

    @Autowired
    TimeTableBellRepository timeTableBellRepository;

    public TimeTableBell addTimeTableBell(TimeTableBell timeTableBell){
       return this.timeTableBellRepository.save(timeTableBell);
    }
    public List<TimeTableBell> findAllTimeTableBell(){
        return  this.timeTableBellRepository.findAll();
    }

    public TimeTableBell findById(int id){
        return  timeTableBellRepository.findById(id);
    }

    public void deleteById(int id){
        timeTableBellRepository.deleteById(id);
    }

}
