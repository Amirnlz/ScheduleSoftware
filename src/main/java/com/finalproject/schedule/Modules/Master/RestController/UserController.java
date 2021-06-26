package com.finalproject.schedule.Modules.Master.RestController;

import com.finalproject.schedule.Modules.Announcements.service.AnnounceService;
import com.finalproject.schedule.Modules.Course.model.Course;
import com.finalproject.schedule.Modules.Master.model.Master;
import com.finalproject.schedule.Modules.Master.service.MasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    MasterService masterService;
    @Autowired
    AnnounceService announceService;

    @DeleteMapping("/Users/:{id}")
    public ResponseEntity<Master> deletebyId(@PathVariable String id){
        masterService.deleteByemail(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/Users/{id}")
    public ResponseEntity<Master>update(@RequestBody Master master){
        Master upUser=masterService.saveUser(master);
        return  ResponseEntity.ok().body(upUser);
    }

    @PostMapping(value = "/Users/Add")
    public ResponseEntity AddSingleUser(@RequestBody Master master){

        masterService.saveUser(master);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public List<Master> getUsers() {
        return masterService.findAllUsers();
    }




}
