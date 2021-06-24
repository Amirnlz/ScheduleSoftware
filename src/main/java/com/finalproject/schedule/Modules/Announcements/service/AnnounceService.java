package com.finalproject.schedule.Modules.Announcements.service;

import com.finalproject.schedule.Modules.Announcements.model.Announce;
import com.finalproject.schedule.Modules.Announcements.repository.AnnounceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AnnounceService {

    private AnnounceRepository announceRepository;

    @Autowired
    public AnnounceService(AnnounceRepository announceRepository) {
        this.announceRepository=announceRepository;
    }

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


}


