package com.finalproject.schedule.Modules.TimeTabelBell.restcontroller;

import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Bell.service.BellService;
import com.finalproject.schedule.Modules.Course.service.CourseService;
import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.Day.service.DayService;
import com.finalproject.schedule.Modules.Master.service.MasterCourseService;
import com.finalproject.schedule.Modules.TimeTabelBell.model.TimeTableBell;
import com.finalproject.schedule.Modules.TimeTabelBell.service.TimeTableBellService;
import com.finalproject.schedule.Modules.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/TimeTableBell")
public class TimeTableRestController {

    DayService dayService;
    BellService bellService;
    TimeTableBellService timeTableBellService;

    @Autowired
    public TimeTableRestController(DayService dayService, BellService bellService, TimeTableBellService timeTableBellService) {
        this.dayService = dayService;
        this.bellService = bellService;
        this.timeTableBellService = timeTableBellService;
    }

    @PostMapping(value = "")
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

    @GetMapping(value = "")
    public List<TimeTableBell>getAllTimeTableBells(){
        return  this.timeTableBellService.findAllTimeTableBell();
    }
}
