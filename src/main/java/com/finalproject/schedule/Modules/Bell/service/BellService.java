package com.finalproject.schedule.Modules.Bell.service;

import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Bell.repository.BellRepository;
import com.finalproject.schedule.Modules.TimeTableBell.model.TimeTableBell;
import com.finalproject.schedule.Modules.TimeTableBell.repository.TimeTableBellRepository;
import com.finalproject.schedule.Modules.User.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public List<Bell>findPaginated(int pageNumber, int pageSize){
        Pageable paging= PageRequest.of(pageNumber,pageSize);
        Page<Bell> page=bellRepository.findAll(paging);
        return page.toList();
    }

}


