//package com.finalproject.schedule.Modules.Master.service;
//
//import com.finalproject.schedule.Modules.Master.model.TimeTable;
//import com.finalproject.schedule.Modules.Master.repository.TimeTableRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//
//import java.io.IOException;
//import java.lang.reflect.InvocationTargetException;
//import java.util.List;
//public class TimeTableService {
//
//    private TimeTableRepository timetableRepository;
//
//    @Autowired
//    public TimeTableService(TimeTableRepository timetableRepository) {
//        this.timetableRepository = timetableRepository;
//    }
//
//
//    public TimeTable registerTimeTable(TimeTable timetable) throws IOException, InvocationTargetException, IllegalAccessException {
//        return this.timetableRepository.save(timetable);
//    }
//
//    public List<TimeTable> findAllTimeTables() {
//        return this.timetableRepository.findAll();
//    }
//}
