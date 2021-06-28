package com.finalproject.schedule.Modules.TimeTabelBell.service;

import com.finalproject.schedule.Modules.TimeTabelBell.model.TimeTableBell;
import com.finalproject.schedule.Modules.TimeTabelBell.repository.TimeTableBellRepository;
import io.swagger.annotations.Authorization;
import org.apache.catalina.LifecycleState;
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
}
