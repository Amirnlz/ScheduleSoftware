package com.finalproject.schedule.Modules.TimeTable.service;

import com.finalproject.schedule.Modules.Master.model.MasterCourse;
import com.finalproject.schedule.Modules.TimeTable.model.TimeTable;
import com.finalproject.schedule.Modules.TimeTable.repository.TimeTableRepository;
import com.finalproject.schedule.Modules.User.model.User;
import com.finalproject.schedule.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Service
public class TimeTableService {

    private TimeTableRepository timetableRepository;

    @Autowired
    public TimeTableService(TimeTableRepository timetableRepository) {
        this.timetableRepository = timetableRepository;
    }


    public TimeTable registerTimeTable(TimeTable timetable) throws IOException, InvocationTargetException, IllegalAccessException {
        return this.timetableRepository.save(timetable);
    }

    public List<TimeTable> findAllTimeTables() {
        return this.timetableRepository.findAll();
    }

    public void deleteById(int id){
        timetableRepository.deleteById(id);
    }

    public  List<TimeTable>findTimeTable(int id){
        List<TimeTable>temp=new ArrayList<>();
        for (TimeTable timetable:timetableRepository.findAll()){
            if(timetable.getCourse().getCourseNumber() == id){
                temp.add(timetable);
            }
        }
        return temp;
    }

    public  TimeTable findById(int id){
        return timetableRepository.findById(id);
    }

    public  List<TimeTable>findDaynumber(String day){
        List<TimeTable>temp=new ArrayList<>();
        for (TimeTable timetable:timetableRepository.findAll()){
            if(timetable.getTimetablebell().getDay().getLabel().contentEquals(day)){
                temp.add(timetable);
            }
        }
        return temp;
    }

    public List<TimeTable> findByStudentId(int id){
        List<TimeTable>timeTableList=new ArrayList<>();
        for(TimeTable timeTable:timetableRepository.findAll()){
           if(timeTable.getUser().getRoles().get(0).toString().equals((Roles.STUDENT).toString())&&timeTable.getUser().getId()==id)
               timeTableList.add(timeTable);
        }
        return timeTableList;
    }

    public List<TimeTable> findByCourseId(int id){
        List<TimeTable>timeTableList=new ArrayList<>();
        for(TimeTable timeTable:timetableRepository.findAll()){
            if(timeTable.getCourse().getCourseNumber()==id)
                timeTableList.add(timeTable);
        }
        return timeTableList;
    }

    public List<TimeTable> findByMasterId(int id){
        List<TimeTable>timeTableList=new ArrayList<>();
        for(TimeTable timeTable:timetableRepository.findAll()){
            if(timeTable.getUser().getRoles().get(0).toString().equals((Roles.MASTER).toString())&&timeTable.getUser().getId()==id)
                timeTableList.add(timeTable);
        }
        return timeTableList;
    }


    public List<TimeTable>findPaginated(int pageNumber,int pageSize){
        Pageable paging= PageRequest.of(pageNumber,pageSize);
        Page<TimeTable> page=timetableRepository.findAll(paging);
        return page.toList();
    }

}
