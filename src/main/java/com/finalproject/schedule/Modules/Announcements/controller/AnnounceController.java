package com.finalproject.schedule.Modules.Announcements.controller;

import com.finalproject.schedule.Modules.Master.service.MasterCourseService;
import com.finalproject.schedule.Modules.TimeTable.model.TimeTable;
import com.finalproject.schedule.Modules.TimeTable.service.TimeTableService;
import com.finalproject.schedule.Modules.TimeTableBell.service.TimeTableBellService;
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
public class AnnounceController {

    private TimeTableService timetableService;
    private UserService userService;

    @Autowired
    public AnnounceController(TimeTableService timetableService, UserService userService) {
        this.timetableService = timetableService;
        this.userService = userService;
    }

    @RequestMapping(value = "/master_announcements", method = RequestMethod.GET)
    public String login(Model model, Principal principal) {

        User user = userService.findByEmail(principal.getName());
        List<TimeTable> timetableList = timetableService.findAllTimeTables();
        List<TimeTable> temp2 = new ArrayList<>();
        for (TimeTable timetable: timetableList){
            if (timetable.getUser().equals(user)){
                temp2.add(timetable);
            }
        }
        model.addAttribute("timetable_model",temp2);
        model.addAttribute("profile", userService.findByEmail(principal.getName()));
        return "master/master_announcements";
    }

}


