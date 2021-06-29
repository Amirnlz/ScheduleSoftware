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
public class MasterController {

    private DayService dayService;
    private BellService bellService;
    private CourseService courseService;

    @Autowired
    public MasterController(DayService dayService, BellService bellService, CourseService courseService) {
        this.dayService = dayService;
        this.bellService = bellService;
        this.courseService = courseService;
    }

    @RequestMapping(value = "/master_main")
    public String master_main(Model model){
        return "master/master_main";
    }

    @RequestMapping(value = "/master_course", method = RequestMethod.GET)
    public String course(Model model) {
        model.addAttribute("course_model",courseService.findAllCourses());
        return "master/master_course";
    }

    @RequestMapping(value = "/master_timetable", method = RequestMethod.GET)
    public String TimeTable(Model model) {

        List<Day> dayList=dayService.findAllDays();
        List<Bell> bellList=bellService.findAllBells();
        model.addAttribute("timetabel_day_model",dayList);
        model.addAttribute("timetabel_bell_model",bellList);

        return "master/master_timetable";
    }




}
