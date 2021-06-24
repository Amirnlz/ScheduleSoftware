package com.finalproject.schedule.Modules.Student.repository;

import com.finalproject.schedule.Modules.Student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {

    Student findById(int id);

}
