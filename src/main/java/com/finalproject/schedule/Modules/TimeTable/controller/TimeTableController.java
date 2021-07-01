package com.finalproject.schedule.Modules.TimeTable.controller;

import com.finalproject.schedule.Modules.Master.model.MasterCourse;
import com.finalproject.schedule.Modules.Master.service.MasterCourseService;
import com.finalproject.schedule.Modules.TimeTable.model.TimeTable;
import com.finalproject.schedule.Modules.TimeTable.service.TimeTableService;
import com.finalproject.schedule.Modules.TimeTableBell.model.TimeTableBell;
import com.finalproject.schedule.Modules.TimeTableBell.service.TimeTableBellService;
import com.finalproject.schedule.Modules.User.model.User;
import com.finalproject.schedule.Modules.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TimeTableController {

    private TimeTableService timetableService;
    private MasterCourseService mastercourseService;
    private UserService userService;
    private TimeTableBellService timetablebellService;

    @Autowired
    public TimeTableController(MasterCourseService mastercourseService, UserService userService, TimeTableBellService timetablebellService, TimeTableService timetableService) {
        this.mastercourseService = mastercourseService;
        this.userService = userService;
        this.timetablebellService = timetablebellService;
        this.timetableService = timetableService;
    }

    @RequestMapping(value = "/master_timetable", method = RequestMethod.GET)
    public String TimeTable(Model model, Principal principal) {
        model.addAttribute("new_timetable",new TimeTable());
        User user = userService.findByEmail(principal.getName());
        List<MasterCourse> mastercourseList = mastercourseService.findAllMasterCourses();
        List<MasterCourse> temp = new ArrayList<>();
        for (MasterCourse mastercourse: mastercourseList){
            if (mastercourse.getUser().equals(user)){
                temp.add(mastercourse);
            }
        }
        model.addAttribute("master_course_model",temp);
        model.addAttribute("timetablebell_model",timetablebellService.findAllTimeTableBell());
        model.addAttribute("timetable_model",timetableService.findAllTimeTables());
        return "master/master_timetable";
    }

    @RequestMapping(value = "/master_timetable/addtimetable", method = RequestMethod.POST)
    public String addtimetable(@ModelAttribute(name = "timetable") TimeTable timetable, Principal principal) throws IOException, InvocationTargetException, IllegalAccessException {
        timetable.setUser(userService.findByEmail(principal.getName()));
        timetableService.registerTimeTable(timetable);
        return "redirect:/master_timetable";
    }


}
