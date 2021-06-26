package com.finalproject.schedule.Modules.User.controller;

import com.finalproject.schedule.Modules.Announcements.model.Announce;
import com.finalproject.schedule.Modules.Announcements.service.AnnounceService;
import org.springframework.beans.factory.annotation.Autowired;
import com.finalproject.schedule.Modules.User.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.finalproject.schedule.Modules.User.model.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Controller
@RequestMapping("/Users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String master(Model model){
        model.addAttribute("user", new User());//object - used in form
        model.addAttribute("user_model", userService.findAllUsers());//show in tabel
        return "admin/admin_user";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registermaster(@ModelAttribute User master) throws IOException, InvocationTargetException, IllegalAccessException {
        userService.registerUser(master);
        return "redirect:/Users";
    }

    @RequestMapping(value = "/delete/{codemelli}", method = RequestMethod.GET)
    public String delete(@PathVariable("codemelli") String codemelli) {
        userService.deleteByCodemelli(codemelli);
        return "redirect:/Users";
    }



    @Autowired
    AnnounceService announceService;

    @PostMapping(value = "Master/:{id}")
    public ResponseEntity addAnnounce(@PathVariable("id")int id, @ModelAttribute Announce announce ){

      /*  Master master=masterService.findById(id);
        Announce newAnnounce=new Announce();
        newAnnounce.setRole(master.getRoles().get(0).toString());
        newAnnounce.setAdded_by(master.getName());
        master.getAnnounceList().add(newAnnounce);
        masterService.saveUser(master);
        announceService.addAnnounce(newAnnounce);*/
        return  ResponseEntity.ok().build();
    }

}
