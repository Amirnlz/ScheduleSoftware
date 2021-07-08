package com.finalproject.schedule.Modules.Student.service;

import com.finalproject.schedule.Modules.Student.model.StudentCourse;
import com.finalproject.schedule.Modules.Student.repository.StudentCourseRepository;
import com.finalproject.schedule.Modules.TimeTable.model.TimeTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentCourseService {

    private StudentCourseRepository studentCourseRepository;

    @Autowired
    public StudentCourseService(StudentCourseRepository studentCourseRepository) {
        this.studentCourseRepository = studentCourseRepository;
    }

    public StudentCourse addCourse(StudentCourse studentCourse) {
        return studentCourseRepository.save(studentCourse);
    }

    public List<StudentCourse> findAllStudentCourses() {
        return studentCourseRepository.findAll();
    }

    public StudentCourse findById(int id){
        return studentCourseRepository.findById(id);
    }

    public void deleteById(int id){
        studentCourseRepository.deleteById(id);
    }

}
