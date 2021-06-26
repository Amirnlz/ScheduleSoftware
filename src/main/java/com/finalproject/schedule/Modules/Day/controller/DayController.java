package com.finalproject.schedule.Modules.Day.controller;

import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.Day.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Controller
@RequestMapping("/Days")
public class DayController {

    private DayService dayService;

    @Autowired
    public DayController(DayService dayService) {
        this.dayService = dayService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String day(Model model){
        model.addAttribute("day", new Day());//new day
        model.addAttribute("day_model", dayService.findAllDays());//show day
        return "admin/admin_day";
    }

    @RequestMapping(value = "/addday", method = RequestMethod.POST)
    public String addDay(@ModelAttribute(name = "day") Day day) throws IOException, InvocationTargetException, IllegalAccessException {
        dayService.addDay(day);
        return "redirect:/Days";
    }

}
