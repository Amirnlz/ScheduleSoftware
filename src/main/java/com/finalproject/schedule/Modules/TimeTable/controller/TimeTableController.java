package com.finalproject.schedule.Modules.TimeTable.controller;

import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Bell.service.BellService;
import com.finalproject.schedule.Modules.Course.service.CourseService;
import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.Day.service.DayService;
import com.finalproject.schedule.Modules.Master.model.MasterCourse;
import com.finalproject.schedule.Modules.Master.service.MasterCourseService;
import com.finalproject.schedule.Modules.TimeTabelBell.service.TimeTableBellService;
import com.finalproject.schedule.Modules.User.model.User;
import com.finalproject.schedule.Modules.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TimeTableController {

    private MasterCourseService mastercourseService;
    private UserService userService;
    private TimeTableBellService timetablebellService;

    @Autowired
    public TimeTableController(MasterCourseService mastercourseService, UserService userService, TimeTableBellService timetablebellService) {
        this.mastercourseService = mastercourseService;
        this.userService = userService;
        this.timetablebellService = timetablebellService;
    }

    @RequestMapping(value = "/master_timetable", method = RequestMethod.GET)
    public String TimeTable(Model model, Principal principal) {
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
//        List<Day> dayList=dayService.findAllDays();
//        List<Bell> bellList=bellService.findAllBells();
//        model.addAttribute("timetabel_day_model",dayList);
//        model.addAttribute("timetabel_bell_model",bellList);

        return "master/master_timetable";
    }

}
