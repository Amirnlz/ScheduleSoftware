package com.finalproject.schedule;

import com.finalproject.schedule.Modules.Announcements.service.AnnounceService;
import com.finalproject.schedule.Modules.Bell.service.BellService;
import com.finalproject.schedule.Modules.Day.service.DayService;
import com.finalproject.schedule.Modules.TimeTable.service.TimeTableService;
import com.finalproject.schedule.Modules.User.model.User;
import com.finalproject.schedule.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.finalproject.schedule.Modules.User.service.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private UserService userService;
    private DayService dayService;
    private BellService bellService;
    private AnnounceService announceService;
    private TimeTableService timetableService;

    @Autowired
    public MainController(UserService userService, DayService dayService, BellService bellService, AnnounceService announceService,TimeTableService timetableService) {
        this.userService = userService;
        this.dayService = dayService;
        this.bellService = bellService;
        this.announceService = announceService;
        this.timetableService = timetableService;
    }

    @RequestMapping(value = "/admin_main")
    public String admin_main(Model model, Principal principal) {

        List<User> masterList = userService.findByRoles(Roles.MASTER.toString());
        List<User> adminList = userService.findByRoles(Roles.ADMIN.toString());
        List<User> studentList = userService.findByRoles(Roles.STUDENT.toString());
        model.addAttribute("master_length", masterList.size());
        model.addAttribute("admin_length", adminList.size());
        model.addAttribute("student_length", studentList.size());
        model.addAttribute("day_length", dayService.findAllDays().size());
        model.addAttribute("bell_length", bellService.findAllBells().size());
        model.addAttribute("announce_length", announceService.findAllAnnounce().size());
        model.addAttribute("profile", userService.findByEmail(principal.getName()));

        model.addAttribute("shanbe", timetableService.findDaynumber("شنبه").size());
        model.addAttribute("yekshanbe", timetableService.findDaynumber("یکشنبه").size());
        model.addAttribute("doshanbe", timetableService.findDaynumber("دوشنبه").size());
        model.addAttribute("seshanbe", timetableService.findDaynumber("سه شنبه").size());
        model.addAttribute("charshanbe", timetableService.findDaynumber("چهارشنبه").size());
        model.addAttribute("panjshanbe", timetableService.findDaynumber("پنجشنبه").size());
        model.addAttribute("jome", timetableService.findDaynumber("جمعه").size());

        return "admin/admin_main";
    }



    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "loginpage";
    }

    @RequestMapping(value = {"loginrole", ""}, method = RequestMethod.GET)
    public String loginrole() {
        return "loginrole";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied() {
        return "403";
    }
}