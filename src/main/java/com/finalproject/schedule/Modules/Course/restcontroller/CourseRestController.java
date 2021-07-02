package com.finalproject.schedule.Modules.Course.restcontroller;

import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Course.model.Course;
import com.finalproject.schedule.Modules.Course.service.CourseService;
import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.Master.model.MasterCourse;
import com.finalproject.schedule.Modules.Master.service.MasterCourseService;
import com.finalproject.schedule.Modules.TimeTable.model.TimeTable;
import com.finalproject.schedule.Modules.TimeTable.service.TimeTableService;
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
@RequestMapping("/api/Courses")
public class CourseRestController {

    private CourseService courseService;
    private MasterCourseService mastercourseService;
    private TimeTableService timetableService;
    private UserService userService;

    @Autowired
    public CourseRestController(CourseService courseService, MasterCourseService mastercourseService, TimeTableService timetableService, UserService userService) {
        this.courseService = courseService;
        this.mastercourseService = mastercourseService;
        this.timetableService = timetableService;
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Course addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Course> getCourses() {
        return courseService.findAllCourses();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findDayById(@PathVariable("id")int id){
        Optional<Course> foundedCourse= Optional.ofNullable(courseService.findById(id));
        return foundedCourse.map(response-> ResponseEntity.ok().body(response)).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity <Course> update(@RequestBody Course course){
        courseService.addCourse(course);
        return  ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity <Course> deletebyId(@PathVariable int id){
        courseService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/{id}/TimeTables", method = RequestMethod.GET)
    public List<TimeTable> findTimeTableByCourseNumber(@PathVariable("id")int id){
        return timetableService.findTimeTable(id);
    }

    @RequestMapping(value = "/{id}/Masters", method = RequestMethod.GET)
    public List<User> findUserByCourseNumber(@PathVariable("id")int id){
        return mastercourseService.findMaster(id);
    }

    @PostMapping(value = "/{id}/choose")
    public ResponseEntity setMasterCourse(@PathVariable("id") int coursenumber){
        Course course=courseService.findById(coursenumber);
        if(course!=null){
            System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
            MasterCourse mastercourse=new MasterCourse();
            mastercourse.setCourse(course);
            mastercourse.setUser(userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
            mastercourseService.addMasterCourse(mastercourse);
        }

        return  ResponseEntity.ok().build();
    }

}



