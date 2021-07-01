package com.finalproject.schedule.Modules.Master.restcontroller;

import com.finalproject.schedule.Modules.Course.model.Course;
import com.finalproject.schedule.Modules.Course.service.CourseService;
import com.finalproject.schedule.Modules.Master.model.MasterCourse;
import com.finalproject.schedule.Modules.Master.service.MasterCourseService;
import com.finalproject.schedule.Modules.User.model.User;
import com.finalproject.schedule.Modules.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import sun.plugin.liveconnect.SecurityContextHelper;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/MasterCourse")
public class MasterCourseRestController {

    private MasterCourseService mastercourseService;
    private CourseService courseService;
    private UserService userService;

    @Autowired
    public MasterCourseRestController(MasterCourseService mastercourseService, CourseService courseService, UserService userService) {
        this.mastercourseService = mastercourseService;
        this.courseService = courseService;
        this.userService = userService;
    }

    @PostMapping(value = "")
    public ResponseEntity setMasterCourse(@RequestParam int coursenumber){

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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<MasterCourse> getMasterCourses() {
        return mastercourseService.findAllMasterCourses();
    }



}

