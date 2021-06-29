package com.finalproject.schedule;

import com.finalproject.schedule.Modules.Announcements.service.AnnounceService;
import com.finalproject.schedule.Modules.Bell.service.BellService;
import com.finalproject.schedule.Modules.Day.service.DayService;
import com.finalproject.schedule.Modules.User.model.User;
import com.finalproject.schedule.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.finalproject.schedule.Modules.User.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private UserService userService;
    @Autowired
    DayService dayService;
    @Autowired
    BellService bellService;
    @Autowired
    AnnounceService announceService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/admin_main")
    public String admin_main(Model model){

        List<User> masterList=userService.findByRoles(Roles.MASTER.toString());
        List<User> adminList=userService.findByRoles(Roles.ADMIN.toString());
        model.addAttribute("master_length",masterList.size());
        model.addAttribute("admin_length",adminList.size());
        model.addAttribute("day_length",dayService.findAllDays().size());
        model.addAttribute("bell_length",bellService.findAllBells().size());
        model.addAttribute("announce_length",announceService.findAllAnnounce().size());
        return "admin/admin_main";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "loginpage";
    }

    @RequestMapping(value = {"loginrole", ""}, method = RequestMethod.GET)
    public String loginrole(){
        return "loginrole";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied() {
        return "403";
    }
}