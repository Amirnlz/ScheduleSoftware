package com.finalproject.schedule.Modules.Announcements.controller;

import com.finalproject.schedule.Modules.Announcements.service.AnnounceService;
import com.finalproject.schedule.Modules.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller

public class AnnounceController {

    @Autowired
    UserService userService;
    @Autowired
    AnnounceService announceService;


   /* @PostMapping(value = "Master/:{id}")
    public ResponseEntity addAnnounce(@PathVariable("id")int id,@ModelAttribute Announce announce ){

       /* Master master=masterService.findById(id);
        Announce newAnnounce=new Announce();
        newAnnounce.setRole(master.getRoles().get(0).toString());
        newAnnounce.setAdded_by(master.getName());
        master.getAnnounceList().add(newAnnounce);
        masterService.saveUser(master);
        announceService.addAnnounce(newAnnounce);
        return  ResponseEntity.ok().build();
    }*/

}


