package com.finalproject.schedule.Modules.Master.service;

import com.finalproject.schedule.Modules.Master.model.MasterCourse;
import com.finalproject.schedule.Modules.Master.repository.MasterCourseRepository;
import com.finalproject.schedule.Modules.User.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MasterCourseService {

    private MasterCourseRepository mastercourseRepository;

    @Autowired
    public MasterCourseService(MasterCourseRepository mastercourseRepository) {
        this.mastercourseRepository = mastercourseRepository;
    }

    public MasterCourse addMasterCourse(MasterCourse mastercourse) {
        return this.mastercourseRepository.save(mastercourse);
    }

    public List<MasterCourse> findAllMasterCourses() {
        return this.mastercourseRepository.findAll();
    }

    public MasterCourse findByUser(User user){
        return this.mastercourseRepository.findByUser(user);
    }

    public void deleteById(int id){
        mastercourseRepository.deleteById(id);
    }

}
