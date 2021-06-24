package com.finalproject.schedule.Modules.Announcements.controller;

import com.finalproject.schedule.Modules.Announcements.model.Announce;
import com.finalproject.schedule.Modules.Announcements.service.AnnounceService;
import com.finalproject.schedule.Modules.Master.model.Master;
import com.finalproject.schedule.Modules.Master.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Controller

public class AnnounceController {

    @Autowired
    MasterService masterService;
    @Autowired
    AnnounceService announceService;


    @PostMapping(value = "Master/:{id}")
    public ResponseEntity addAnnounce(@PathVariable("id")int id,@ModelAttribute Announce announce ){

        Master master=masterService.findById(id);
        Announce newAnnounce=new Announce();
        newAnnounce.setRole(master.getRoles().get(0).toString());
        newAnnounce.setAdded_by(master.getName());
        master.getAnnounceList().add(newAnnounce);
        masterService.saveUser(master);
        announceService.addAnnounce(newAnnounce);
        return  ResponseEntity.ok().build();
    }

}


