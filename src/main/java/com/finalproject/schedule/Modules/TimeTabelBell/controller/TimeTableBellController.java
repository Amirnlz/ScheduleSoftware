package com.finalproject.schedule.Modules.TimeTabelBell.controller;

import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Bell.service.BellService;
import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.Day.service.DayService;
import com.finalproject.schedule.Modules.Master.model.TimeTable;
import com.finalproject.schedule.Modules.TimeTabelBell.model.TimeTableBell;
import com.finalproject.schedule.Modules.TimeTabelBell.service.TimeTableBellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TimeTableBellController {

    @Autowired
    TimeTableBellService timeTableBellService;

    @RequestMapping(value = "/timetabelbell", method = RequestMethod.GET)
    public String timetabelbell(Model model){

        List<TimeTableBell>timeTableBellList=timeTableBellService.allTimeTabeBells();
        model.addAttribute("timetabelbell_model",timeTableBellList);
        return "admin/admin_timetabelbell";
    }
}
