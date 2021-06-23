package com.finalproject.schedule.Modules.Bell.controller;

import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Bell.service.BellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

@Controller
@RequestMapping("/Bells")
public class BellController {

    private BellService bellService;

    @Autowired
    public BellController(BellService bellService) {
        this.bellService = bellService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String bell(Model model){
        model.addAttribute("bell", new Bell());//new bell
        model.addAttribute("bell_model", bellService.findAllBells());//show bell
        return "bell";
    }

    @RequestMapping(value = "/addbell", method = RequestMethod.POST)
    public String addBell(@ModelAttribute(name = "bell") Bell bell) throws IOException, InvocationTargetException, IllegalAccessException {
        bellService.addBell(bell);
        return "redirect:/Bells";
    }

}


