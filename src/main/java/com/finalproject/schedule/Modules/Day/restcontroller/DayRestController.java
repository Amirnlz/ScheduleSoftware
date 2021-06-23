package com.finalproject.schedule.Modules.Day.restcontroller;

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
public class DayRestController {

    private DayService dayService;

    @Autowired
    public DayRestController(DayService dayService) {
        this.dayService = dayService;
    }

    @RequestMapping(value = "/rest/addDay", method = RequestMethod.POST)
    public Day addDay(@RequestBody Day day) {
        return dayService.addDay(day);
    }

    @RequestMapping(value = "/addDay/getDays", method = RequestMethod.GET)
    public List<Day> getDays() {
        return dayService.findAllDays();
    }
}

