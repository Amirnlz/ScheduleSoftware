package com.finalproject.schedule.Modules.Course.restcontroller;

import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Course.model.Course;
import com.finalproject.schedule.Modules.Course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Courses")
public class CourseRestController {

    private CourseService courseService;

    @Autowired
    public CourseRestController(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(value = "/rest/addCourse", method = RequestMethod.POST)
    public Course addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @RequestMapping(value = "/rest/getCourses", method = RequestMethod.GET)
    public List<Course> getCourses() {
        return courseService.findAllCourses();
    }

    //find day by id
    @RequestMapping(value = "/Cours/:{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findDayById(@PathVariable("id")int id){

        Optional<Course> foundedCourse= Optional.ofNullable(courseService.findById(id));
        return foundedCourse.map(response-> ResponseEntity.ok().body(response)).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //deete bell by id
    @DeleteMapping("/Course/:{id}")
    public ResponseEntity<Course> deletebyId(@PathVariable int id){
        courseService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "/Bells/{id}")
    public ResponseEntity<Course>update(@RequestBody Course course){
        Course upCourse=courseService.addCourse(course);
        return  ResponseEntity.ok().body(upCourse);
    }

}



