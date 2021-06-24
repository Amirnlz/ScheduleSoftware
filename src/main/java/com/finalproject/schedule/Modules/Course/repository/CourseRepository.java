package com.finalproject.schedule.Modules.Course.repository;

import com.finalproject.schedule.Modules.Course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Course findById(int id);
    Course deleteById(int id);
}
