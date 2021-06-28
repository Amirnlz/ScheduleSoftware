//package com.finalproject.schedule.Modules.TimeTabelBell.restcontroller;
//
//import com.finalproject.schedule.Modules.Day.model.Day;
//import com.finalproject.schedule.Modules.Day.service.DayService;
//import com.finalproject.schedule.Modules.TimeTabelBell.model.TimeTableBell;
//import com.finalproject.schedule.Modules.TimeTabelBell.service.TimeTableBellService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/TimeTableBell")
//public class TimeTableBellRestController {
//
//    private TimeTableBellService timetablebellService;
//
//    @Autowired
//    public TimeTableBellRestController(TimeTableBellService timetablebellService) {
//        this.timetablebellService = timetablebellService;
//    }
//
//    @RequestMapping(value = "/rest/addTimeTableBell", method = RequestMethod.POST)
//    public TimeTableBell addTimeTableBell(@RequestBody TimeTableBell timetablebell) {
//        return timetablebellService.addTimeTableBell(timetablebell);
//    }
//
//    @RequestMapping(value = "/rest/getTimeTableBells", method = RequestMethod.GET)
//    public List<TimeTableBell> getTimeTableBells() {
//        return timetablebellService.findAllTimeTableBell();
//    }
//
//}
