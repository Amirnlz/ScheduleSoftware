package com.finalproject.schedule.Modules.Bell.service;

import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Bell.repository.BellRepository;
import com.finalproject.schedule.Modules.TimeTableBell.model.TimeTableBell;
import com.finalproject.schedule.Modules.TimeTableBell.repository.TimeTableBellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BellService {

    private BellRepository bellRepository;
    private TimeTableBellRepository timetablebellRepository;

    @Autowired
    public BellService(BellRepository bellRepository, TimeTableBellRepository timetablebellRepository) {
        this.bellRepository = bellRepository;
        this.timetablebellRepository = timetablebellRepository;
    }

    @Transactional
    public Bell addBell(Bell bell) {
        return this.bellRepository.save(bell);
    }

    public List<Bell> findAllBells() {
        return this.bellRepository.findAll();
    }

    public  Bell findById(int id){
        return this.bellRepository.findById(id);
    }

    public  Bell deleteById(int id){
        for (TimeTableBell timetablebell:timetablebellRepository.findAll()){
            if(timetablebell.getBell().getId() == id){
                timetablebellRepository.deleteById(timetablebell.getId());
            }
        }
        return  this.bellRepository.deleteById(id);
    }

}


