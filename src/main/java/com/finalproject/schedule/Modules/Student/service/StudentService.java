package com.finalproject.schedule.Modules.Student.service;

import com.finalproject.schedule.Modules.Student.model.Student;
import com.finalproject.schedule.Modules.Student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository= studentRepository;
    }

    public Student findById(int id){
        Student student=studentRepository.findById(id);
        return  student;
    }

    public  List<Student>getAllStudents(){
        return  studentRepository.findAll();
    }

}
