package com.finalproject.schedule.Modules.Course.controller;

import com.finalproject.schedule.Modules.Course.model.Course;
import com.finalproject.schedule.Modules.Course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Courses")
public class CourseController {

    private CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(value = {"/",""}, method = RequestMethod.POST)
    public Course addBell(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @RequestMapping(value = {"/",""}, method = RequestMethod.GET)
    public List<Course> getCourses() {
        return courseService.findAllCourses();
    }

}



