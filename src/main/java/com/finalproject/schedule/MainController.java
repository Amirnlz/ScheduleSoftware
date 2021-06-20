package com.finalproject.schedule;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

    @RequestMapping(value = {"", "/main"})
    public String index(){
        return "main";
    }

    @RequestMapping(value = "/master")
    public String master(){
        return "master";
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

    @RequestMapping(value = "/exit")
    public String loginpage(){
        return "loginpage";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String accessDenied() {
        return "403";
    }
}