package com.finalproject.schedule.Modules.Announcements.service;

import com.finalproject.schedule.Modules.Announcements.model.Announce;
import com.finalproject.schedule.Modules.Announcements.repository.AnnounceRepository;
import com.finalproject.schedule.Modules.TimeTable.model.TimeTable;
import com.finalproject.schedule.Modules.TimeTable.service.TimeTableService;
import com.finalproject.schedule.Modules.User.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnnounceService {

    private AnnounceRepository announceRepository;

    @Autowired
    public AnnounceService(AnnounceRepository announceRepository) {
        this.announceRepository=announceRepository;
    }

    @Autowired
    TimeTableService timeTableService;
    @Transactional
    public Announce addAnnounce(Announce announce) {
        return this.announceRepository.save(announce);
    }

    public List<Announce> findAllAnnounce() {
        return this.announceRepository.findAll();
    }

    public Announce findById(int id){
        return this.announceRepository.findById(id);
    }

    public Announce deleteById(int id){
        return  this.announceRepository.deleteById(id);
    }

    public List<Announce>findByTimeTableId(int id){
        List<Announce>announceList=announceRepository.findAll();
        List<Announce>foundedAnnounces=new ArrayList<>();
        for(Announce announce:announceList){
            if(announce.getTimeTable().getId()==id)
                foundedAnnounces.add(announce);
        }
        return foundedAnnounces;
    }

    public List<Announce>findByMasterId(int masterId){

        List<Announce>announceList=new ArrayList<>();
        for(Announce announce:announceRepository.findAll()){
            if(announce.getTimeTable().getUser().getId()==masterId)
                announceList.add(announce);
        }
        return announceList;
    }
    public List<Announce>findPaginated(int pageNumber, int pageSize){
        Pageable paging= PageRequest.of(pageNumber,pageSize);
        Page<Announce> page=announceRepository.findAll(paging);
        return page.toList();
    }

}


