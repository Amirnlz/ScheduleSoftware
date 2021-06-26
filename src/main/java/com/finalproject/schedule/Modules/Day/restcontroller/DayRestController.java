package com.finalproject.schedule.Modules.Day.restcontroller;

import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.Day.service.DayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Days")
public class DayRestController {

    private DayService dayService;

    @Autowired
    public DayRestController(DayService dayService) {
        this.dayService = dayService;
    }

    @RequestMapping(value = "/rest/addDay", method = RequestMethod.POST)
    public Day addDay(@RequestBody Day day) {
        return dayService.addDay(day);
    }

    @RequestMapping(value = "/addDay/getDays", method = RequestMethod.GET)
    public List<Day> getDays() {
        return dayService.findAllDays();
    }

    //find day by id
    @RequestMapping(value = "/Days/:{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findDayById(@PathVariable("id")int id){

        Optional<Day> foundedDay= Optional.ofNullable(dayService.findById(id));
        return foundedDay.map(response-> ResponseEntity.ok().body(response)).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/Days/:{id}")
    public ResponseEntity<Day> deletebyId(@PathVariable int id){
        dayService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/Days/{id}")
    public ResponseEntity<Day>update(@RequestBody Day day){
        Day upDay=dayService.addDay(day);
        return  ResponseEntity.ok().body(upDay);
    }
}

