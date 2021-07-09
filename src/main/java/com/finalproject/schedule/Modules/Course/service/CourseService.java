package com.finalproject.schedule.Modules.Course.service;

import com.finalproject.schedule.Modules.Announcements.model.Announce;
import com.finalproject.schedule.Modules.Course.model.Course;
import com.finalproject.schedule.Modules.Course.repository.CourseRepository;
import com.finalproject.schedule.Modules.Master.model.MasterCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.finalproject.schedule.Modules.Master.repository.MasterCourseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private MasterCourseRepository MasterCourseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, MasterCourseRepository MasterCourseRepository) {
        this.courseRepository = courseRepository;
        this.MasterCourseRepository = MasterCourseRepository;
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

    public void deleteById(int id){
        for (MasterCourse mastercourse:MasterCourseRepository.findAll()){
            if(mastercourse.getCourse().getCourseNumber() == id){
                MasterCourseRepository.deleteById(mastercourse.getId());
            }
        }
        courseRepository.deleteById(id);
    }

    public List<Course>findByUnitCounts(int count){
        List<Course>courseList=courseRepository.findAll();
        List<Course>foundedCourses=new ArrayList<>();
        for(Course course:courseList){
            if(course.getUnitsCount()==count)
               foundedCourses.add(course);
        }
        return foundedCourses;
    }

    public List<Course>findByTitle(String title){

        List<Course>courseList=new ArrayList<>();
        for(Course course:courseRepository.findAll()){
            if(course.getTitle().contains(title))
                courseList.add(course);
        }
        return courseList;
    }
    public List<Course>findPaginated(int pageNumber, int pageSize){
        Pageable paging= PageRequest.of(pageNumber,pageSize);
        Page<Course> page=courseRepository.findAll(paging);
        return page.toList();
    }
}
