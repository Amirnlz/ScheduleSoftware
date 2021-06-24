package com.finalproject.schedule.Modules.Master.controller;

import com.finalproject.schedule.Modules.Announcements.model.Announce;
import com.finalproject.schedule.Modules.Announcements.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import com.finalproject.schedule.Modules.Master.service.MasterService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.finalproject.schedule.Modules.Master.model.Master;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Controller
@RequestMapping("/Users")
public class MasterController {

    private MasterService masterService;

    @Autowired
    public MasterController(MasterService masterService) {
        this.masterService = masterService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String master(Model model){
        model.addAttribute("master", new Master());//object - used in form
        model.addAttribute("master_model", masterService.findAllUsers());//show in tabel
        return "master";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registermaster(@ModelAttribute(name = "master") Master master) throws IOException, InvocationTargetException, IllegalAccessException {
        masterService.registerUser(master);
        return "redirect:/Users";
    }


    @Autowired
    AnnounceService announceService;


    @PostMapping(value = "Master/:{id}")
    public ResponseEntity addAnnounce(@PathVariable("id")int id, @ModelAttribute Announce announce ){

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
