package com.finalproject.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.finalproject.schedule.Modules.User.service.UserService;

@Controller
public class MainController {

    private UserService userService;

    @Autowired
    public MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/admin_main")
    public String admin_main(Model model){
        model.addAttribute("main_model", userService.findAllUsers());
        return "admin/admin_main";
    }

    @RequestMapping(value = "/master_main")
    public String master_main(Model model){
        return "master/master_main";
    }

    @RequestMapping(value = "/master_free")
    public String masterfree(Model model){
        return "master/master_freetime";
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