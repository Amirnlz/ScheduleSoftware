package com.finalproject.schedule.Modules.Master.service;

import com.finalproject.schedule.Modules.Course.model.Course;
import com.finalproject.schedule.Modules.Master.model.MasterCourse;
import com.finalproject.schedule.Modules.Master.repository.MasterCourseRepository;
import com.finalproject.schedule.Modules.TimeTable.model.TimeTable;
import com.finalproject.schedule.Modules.TimeTable.repository.TimeTableRepository;
import com.finalproject.schedule.Modules.User.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MasterCourseService {

    private MasterCourseRepository mastercourseRepository;
    private TimeTableRepository timetableRepository;

    @Autowired
    public MasterCourseService(MasterCourseRepository mastercourseRepository, TimeTableRepository timetableRepository) {
        this.mastercourseRepository = mastercourseRepository;
        this.timetableRepository = timetableRepository;
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

    public MasterCourse findById(int id){
        return this.mastercourseRepository.findById(id);
    }

    public void deleteById(int id){
        for (TimeTable timetable:timetableRepository.findAll()){
            if(timetable.getCourse().getCourseNumber() == mastercourseRepository.findById(id).getCourse().getCourseNumber()){
                timetableRepository.deleteById(timetable.getId());
            }
        }
        mastercourseRepository.deleteById(id);
    }

    public  List<User>findMaster(int id){
        List<User>temp=new ArrayList<>();
        for (MasterCourse mastercourse:mastercourseRepository.findAll()){
            if(mastercourse.getCourse().getCourseNumber() == id){
                temp.add(mastercourse.getUser());
            }
        }
        return temp;
    }

}
