package com.finalproject.schedule.Modules.TimeTabelBell.controller;

import com.finalproject.schedule.Modules.Bell.service.BellService;
import com.finalproject.schedule.Modules.Day.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class TimeTableBellController {

    private DayService dayService;
    private BellService bellService;

    @Autowired
    public TimeTableBellController(DayService dayService, BellService bellService) {
        this.dayService = dayService;
        this.bellService = bellService;
    }

    @RequestMapping(value = "/timetabelbell", method = RequestMethod.GET)
    public String timetabelbell(Model model){
        model.addAttribute("day_model", dayService.findAllDays());
        model.addAttribute("bell_model", bellService.findAllBells());
        return "timetabelbell";
    }
}
