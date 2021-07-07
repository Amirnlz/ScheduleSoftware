package com.finalproject.schedule.Modules.Announcements.restcontroller;

import com.finalproject.schedule.Modules.Announcements.model.Announce;
import com.finalproject.schedule.Modules.Announcements.service.AnnounceService;
import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.TimeTable.model.TimeTable;
import com.finalproject.schedule.Modules.TimeTable.service.TimeTableService;
import com.finalproject.schedule.Modules.TimeTableBell.model.TimeTableBell;
import com.finalproject.schedule.Modules.User.model.User;
import com.finalproject.schedule.Modules.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Announcements")
public class AnnounceRestController {

    private AnnounceService announceService;
    private TimeTableService timeTableService;
    private UserService userService;

    @Autowired
    public AnnounceRestController(AnnounceService announceService, TimeTableService timeTableService, UserService userService) {
        this.announceService = announceService;
        this.timeTableService = timeTableService;
        this.userService = userService;
    }

    @PostMapping(value = "")
    public ResponseEntity setAnnouncements(@RequestParam int timeTableId, @RequestParam String message){

        String email=  SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userService.findByEmail(email);
        TimeTable timeTable=timeTableService.findById(timeTableId);

        System.out.println(user.getId()+"     "+timeTable.getUser().getId());
        if(timeTable!=null&&user.getId()==timeTable.getUser().getId()){
            Announce announce=new Announce();
            announce.setMessage(message);
            announce.setTimeTable(timeTable);
            announceService.addAnnounce(announce);
            return  ResponseEntity.ok().build();
        }
        else{
            if(timeTable==null)
                return (ResponseEntity) ResponseEntity.status(HttpStatus.NOT_FOUND);
            else
                return (ResponseEntity) ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }


    }

    @GetMapping(value = "")
    public List<Announce> findDayByTimeTable(@RequestParam int MasterId, @RequestParam int timeTableId){
//        Optional<Announce> foundedAnnounce= Optional.ofNullable(announceService.findByTimeTable(timeTableId));
//        return foundedAnnounce.map(response-> ResponseEntity.ok().body(response)).orElse(
//                new ResponseEntity<>(HttpStatus.NOT_FOUND));
        return announceService.findByTimeTable(timeTableId);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findAnnounceById(@PathVariable int id){

        Optional<Announce> foundedAnnounce= Optional.ofNullable(announceService.findById(id));
        return foundedAnnounce.map(response-> ResponseEntity.ok().body(response)).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Announce> deletebyId(@PathVariable int id){
        announceService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
