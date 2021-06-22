package com.finalproject.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.finalproject.schedule.Modules.Master.service.MasterService;

@Controller
public class MainController {

    private MasterService masterService;

    @Autowired
    public MainController(MasterService masterService) {
        this.masterService = masterService;
    }

    @RequestMapping(value = "/main")
    public String main(){
        return "main";
    }

    @RequestMapping(value = "/day")
    public String day(){
        return "day";
    }

    @RequestMapping(value = "/bell")
    public String bell(){
        return "bell";
    }

    @RequestMapping(value = "/timetabelbell")
    public String timetabelbell(){
        return "timetabelbell";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "loginpage";
    }


    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied() {
        return "403";
    }
}