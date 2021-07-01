package com.finalproject.schedule.Modules.Master.controller;

import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Bell.service.BellService;
import com.finalproject.schedule.Modules.Course.model.Course;
import com.finalproject.schedule.Modules.Course.service.CourseService;
import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.Day.service.DayService;
import com.finalproject.schedule.Modules.Master.model.TimeTableBellForFront;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TimeTableBell {

    @Autowired
    BellService bellService;
    @Autowired
    DayService dayService;

    //TimeTableBellForFront timeTableBellForFront=new TimeTableBellForFront();


    @GetMapping(value = "/timetablebell")
    public String showTimetableBell(Model model){

        List<Bell>bellList=bellService.findAllBells();
        List<Day>dayList=dayService.findAllDays();
        List<TimeTableBellForFront>timeTableBellForFronts=new ArrayList<>();
        System.out.println(bellList.size());

        for(int i=0;i<dayList.size();i++){
            for(int j=0;j<bellList.size();j++){
                TimeTableBellForFront timeTableBellForFront=new TimeTableBellForFront();
                timeTableBellForFront.setDay(dayList.get(i));
                timeTableBellForFront.setBell(bellList.get(j));
                timeTableBellForFronts.add(timeTableBellForFront);
            }
        }
        model.addAttribute("time_tables",timeTableBellForFronts);
        return "admin/admin_timetabelbell";
    }
}
