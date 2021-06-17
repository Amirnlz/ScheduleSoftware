package com.finalproject.schedule.Modules.Master.controller;

import com.finalproject.schedule.Modules.Master.model.Master;
import com.finalproject.schedule.Modules.Master.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Users")
public class MasterController {

    private MasterService masterService;

    @Autowired
    public MasterController(MasterService masterService) {
        this.masterService = masterService;
    }

    @RequestMapping(value = {"/",""}, method = RequestMethod.POST)
    public Master registerUser(@RequestBody Master users) {
        return masterService.registerUser(users);
    }

    @RequestMapping(value = {"/",""}, method = RequestMethod.GET)
    public List<Master> getUsers() {
        return masterService.findAllUsers();
    }

}
