package com.finalproject.schedule.Modules.Day.controller;

import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.Day.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Days")
public class DayController {

    private DayService dayService;

    @Autowired
    public DayController(DayService dayService) {
        this.dayService = dayService;
    }

    @RequestMapping(value = {"/",""}, method = RequestMethod.POST)
    public Day addDay(@RequestBody Day day) {
        return dayService.addDay(day);
    }

    @RequestMapping(value = {"/",""}, method = RequestMethod.GET)
    public List<Day> getDays() {
        return dayService.findAllDays();
    }

}

