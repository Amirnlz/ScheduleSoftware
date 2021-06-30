package com.finalproject.schedule.Modules.Master.repository;

import com.finalproject.schedule.Modules.Master.model.MasterCourse;
import com.finalproject.schedule.Modules.User.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterCourseRepository extends JpaRepository<MasterCourse, Integer> {

    MasterCourse findByUser(User user);

}