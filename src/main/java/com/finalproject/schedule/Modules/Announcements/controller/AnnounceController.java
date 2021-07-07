package com.finalproject.schedule.Modules.Announcements.controller;

import com.finalproject.schedule.Modules.Announcements.model.Announce;
import com.finalproject.schedule.Modules.Announcements.service.AnnounceService;
import com.finalproject.schedule.Modules.TimeTable.model.TimeTable;
import com.finalproject.schedule.Modules.TimeTable.service.TimeTableService;
import com.finalproject.schedule.Modules.User.model.User;
import com.finalproject.schedule.Modules.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AnnounceController {

    private TimeTableService timetableService;
    private UserService userService;
    private AnnounceService announceService;

    @Autowired
    public AnnounceController(TimeTableService timetableService, UserService userService,AnnounceService announceService) {
        this.timetableService = timetableService;
        this.userService = userService;
        this.announceService = announceService;
    }

    @RequestMapping(value = "/master_announcements", method = RequestMethod.GET)
    public String login(Model model, Principal principal) {
        model.addAttribute("new_announce",new Announce());
        User user = userService.findByEmail(principal.getName());

        List<Announce> announceList = announceService.findAllAnnounce();
        List<Announce> temp = new ArrayList<>();
        for (Announce announce: announceList){
            if (announce.getTimeTable().getUser().equals(user)){
                temp.add(announce);
            }
        }
        model.addAttribute("announce_model",temp);
        List<TimeTable> timetableList = timetableService.findAllTimeTables();
        List<TimeTable> temp2 = new ArrayList<>();
        for (TimeTable timetable: timetableList){
            if (timetable.getUser().equals(user) && timetable.getAcceptance() == 1){
                temp2.add(timetable);
            }
        }
        model.addAttribute("timetable_model",temp2);
        model.addAttribute("profile", userService.findByEmail(principal.getName()));
        return "master/master_announcements";
    }

    @RequestMapping(value = "/master_announcements/addannounce", method = RequestMethod.POST)
    public String addannounce(@ModelAttribute(name = "announce") Announce announce) throws IOException, InvocationTargetException, IllegalAccessException {
        announceService.addAnnounce(announce);
        return "redirect:/master_announcements";
    }

    @RequestMapping(value = "/master_announcements/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {
        announceService.deleteById(id);
        return "redirect:/master_announcements";
    }

}


