package com.finalproject.schedule.Modules.Master.controller;

import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Bell.service.BellService;
import com.finalproject.schedule.Modules.Course.service.CourseService;
import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.Day.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TimeTableController {

    private DayService dayService;
    private BellService bellService;
    private CourseService courseService;

    @Autowired
    public TimeTableController(DayService dayService, BellService bellService, CourseService courseService) {
        this.dayService = dayService;
        this.bellService = bellService;
        this.courseService = courseService;
    }

    @RequestMapping(value = "/Master", method = RequestMethod.GET)
    public String course(Model model) {

        List<Day> dayList=dayService.findAllDays();
        List<Bell> bellList=bellService.findAllBells();
        model.addAttribute("timetabel_day_model",dayList);
        model.addAttribute("timetabel_bell_model",bellList);
        model.addAttribute("timetabel_course_model",courseService.findAllCourses());

        return "master/master_freetime";
    }


}
