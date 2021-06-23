package com.finalproject.schedule.Modules.Bell.restcontroller;

import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Bell.service.BellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Bells")
public class BellRestController {

    private BellService bellService;

    @Autowired
    public BellRestController(BellService bellService) {
        this.bellService = bellService;
    }

    @RequestMapping(value = "/rest/addBell", method = RequestMethod.POST)
    public Bell addBell(@RequestBody Bell bell) {
        return bellService.addBell(bell);
    }

    @RequestMapping(value = "/rest/getBells", method = RequestMethod.GET)
    public List<Bell> getBells() {
        return bellService.findAllBells();
    }

}

