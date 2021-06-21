package com.finalproject.schedule.Modules.Master.controller;

import org.springframework.beans.factory.annotation.Autowired;
import com.finalproject.schedule.Modules.Master.service.MasterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.finalproject.schedule.Modules.Master.model.Master;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Controller
public class MasterController {

    private MasterService masterService;

    @Autowired
    public MasterController(MasterService masterService) {
        this.masterService = masterService;
    }

    @RequestMapping(value = "/Users", method = RequestMethod.GET)
    public String showmaster(Model model){
        model.addAttribute("master", new Master());
        model.addAttribute("master_model", masterService.findAllUsers());
        return "master";
    }

    @RequestMapping(value = "/Users", method = RequestMethod.POST)
    public String master(@ModelAttribute Master master) throws IOException, InvocationTargetException, IllegalAccessException {
        masterService.registerUser(master);
        return "master";
    }

}
