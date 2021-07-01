package com.finalproject.schedule.Modules.TimeTableBell.controller;

import com.finalproject.schedule.Modules.Bell.service.BellService;
import com.finalproject.schedule.Modules.Day.service.DayService;
import com.finalproject.schedule.Modules.TimeTableBell.model.TimeTableBell;
import com.finalproject.schedule.Modules.TimeTableBell.service.TimeTableBellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;


@Controller
public class TimeTableBellController {

    TimeTableBellService timeTableBellService;
    DayService dayService;
    BellService bellService;

    @Autowired
    public TimeTableBellController(TimeTableBellService timeTableBellService, DayService dayService, BellService bellService) {
        this.timeTableBellService = timeTableBellService;
        this.dayService = dayService;
        this.bellService = bellService;
    }

    @RequestMapping(value = "/timetabelbell", method = RequestMethod.GET)
    public String timetabelbell(Model model){
        model.addAttribute("new_timetabelbell", new TimeTableBell());
        model.addAttribute("day_model",dayService.findAllDays());
        model.addAttribute("bell_model",bellService.findAllBells());
        model.addAttribute("timetabelbell_model",timeTableBellService.findAllTimeTableBell());
        return "admin/admin_timetabelbell";
    }

    @RequestMapping(value = "/timetabelbell/addtimetabelbell", method = RequestMethod.POST)
    public String addtimetabelbell(@ModelAttribute(name = "timetabelbell") TimeTableBell timetablebell) throws IOException, InvocationTargetException, IllegalAccessException {
        timeTableBellService.addTimeTableBell(timetablebell);
        return "redirect:/timetabelbell";
    }

}
