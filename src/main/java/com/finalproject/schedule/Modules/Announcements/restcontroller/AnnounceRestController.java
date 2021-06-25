package com.finalproject.schedule.Modules.Announcements.restcontroller;

import com.finalproject.schedule.Modules.Announcements.model.Announce;
import com.finalproject.schedule.Modules.Announcements.service.AnnounceService;
import com.finalproject.schedule.Modules.Master.model.Master;
import com.finalproject.schedule.Modules.Master.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController

public class AnnounceRestController {

    private AnnounceService announceService;

    @Autowired
    public AnnounceRestController(AnnounceService announceService) {
        this.announceService=announceService;
    }

    @Autowired
    MasterService masterService;
    @RequestMapping(value = "/Announcements", method = RequestMethod.POST)
    public Announce addAnnounce(@RequestBody Announce announce) {
        return announceService.addAnnounce(announce);
    }

    @RequestMapping(value = "/rest/getBells", method = RequestMethod.GET)
    public List<Announce> getAnnounces(@RequestParam int master_id) {
        List<Announce>announceList=new ArrayList<>();
        for(Announce announce:announceService.findAllAnnounce()){
            if(announce.getMaster_id()==master_id)
                announceList.add(announce);
        }
     return announceList;
    }

    //find announce by id
    @RequestMapping(value = "/Announcements/:{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findDayById(@PathVariable("id")int id){

        Optional<Announce> foundedAnnounce= Optional.ofNullable(announceService.findById(id));
        return foundedAnnounce.map(response-> ResponseEntity.ok().body(response)).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //deete bell by id
    @DeleteMapping("/Announcements/:{id}")
    public ResponseEntity<Announce> deletebyId(@PathVariable int id){
        announceService.deleteById(id);
        return ResponseEntity.ok().build();
    }


}

