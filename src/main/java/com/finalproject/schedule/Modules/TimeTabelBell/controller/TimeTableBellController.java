package com.finalproject.schedule.Modules.TimeTabelBell.controller;

import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Bell.service.BellService;
import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.Day.service.DayService;
import com.finalproject.schedule.Modules.Master.model.TimeTable;
import com.finalproject.schedule.Modules.TimeTabelBell.model.TimeTableBell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TimeTableBellController {

    private DayService dayService;
    private BellService bellService;

    @Autowired
    public TimeTableBellController(DayService dayService, BellService bellService) {
        this.dayService = dayService;
        this.bellService = bellService;
    }

   /* @RequestMapping(value = "/timetabelbell", method = RequestMethod.GET)
    public String timetabelbell(Model model){
        List<Day> dayList=dayService.findAllDays();
        List<Bell> bellList=bellService.findAllBells();
        List<TimeTableBell> timetablebell=new ArrayList<>();
        TimeTableBell timetablebell_obj = new TimeTableBell();
        for(int i=0; i < dayList.size();i++){//2
            for(int j=0; j < bellList.size();j++){//1
                timetablebell_obj = new TimeTableBell();
                timetablebell_obj.setDay(dayList.get(i));
                timetablebell_obj.setBell(bellList.get(j));
                timetablebell.add(timetablebell_obj);
            }
        }

        model.addAttribute("timetabelbell_model",timetablebell);
        return "admin/admin_timetabelbell";
    }*/
}
