package com.finalproject.schedule.Modules.TimeTable.service;

import com.finalproject.schedule.Modules.TimeTable.model.TimeTable;
import com.finalproject.schedule.Modules.TimeTable.repository.TimeTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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

}
