package com.finalproject.schedule.Modules.TimeTabelBell.restController;

import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Bell.service.BellService;
import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.Day.service.DayService;
import com.finalproject.schedule.Modules.Master.model.TimeTable;
import com.finalproject.schedule.Modules.TimeTabelBell.model.TimeTableBell;
import com.finalproject.schedule.Modules.TimeTabelBell.service.TimeTableBellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TimeTableRest {

    @Autowired
    DayService dayService;
    @Autowired
    BellService bellService;
    @Autowired
    TimeTableBellService timeTableBellService;

    @PostMapping(value = "/TimeTableBell")
    public ResponseEntity setTimeTable(@RequestParam int dayId, @RequestParam int bellId){

        Day day=dayService.findById(dayId);
        Bell bell=bellService.findById(bellId);
        if(day!=null&&bell!=null){
            System.out.println("not null");
            TimeTableBell timeTableBell=new TimeTableBell();
            timeTableBell.setBell(bell);
            timeTableBell.setDay(day);
            timeTableBellService.addTimeTableBell(timeTableBell);
        }

        return  ResponseEntity.ok().build();
    }

    @GetMapping(value = "/TimeTableBell")
    public List<TimeTableBell>getAllTimeTableBells(){
        return  this.timeTableBellService.findAllTimeTableBell();
    }
}
