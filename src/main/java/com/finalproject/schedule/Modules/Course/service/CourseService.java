package com.finalproject.schedule.Modules.Course.service;

import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Course.model.Course;
import com.finalproject.schedule.Modules.Course.repository.CourseRepository;
import com.finalproject.schedule.Modules.User.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Transactional
    public Course addCourse(Course course) {
        return this.courseRepository.save(course);
    }

    public List<Course> findAllCourses() {
        return this.courseRepository.findAll();
    }

    public Course findById(int id){
        return courseRepository.findById(id);
    }
}
