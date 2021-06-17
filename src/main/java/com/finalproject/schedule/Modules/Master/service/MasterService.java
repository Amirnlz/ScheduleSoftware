package com.finalproject.schedule.Modules.Master.service;

import com.finalproject.schedule.Modules.Master.model.Master;
import com.finalproject.schedule.Modules.Master.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MasterService {

    private MasterRepository masterRepository;

    @Autowired
    public MasterService(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    @Transactional
    public Master registerUser(Master users) {
        return this.masterRepository.save(users);
    }

    public List<Master> findAllUsers() {
        return this.masterRepository.findAll();
    }

}
