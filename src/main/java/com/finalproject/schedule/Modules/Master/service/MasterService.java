package com.finalproject.schedule.Modules.Master.service;

import com.finalproject.schedule.Modules.Master.model.Master;
import com.finalproject.schedule.Modules.Master.repository.MasterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class MasterService {

    private MasterRepository masterRepository;

    @Autowired
    public MasterService(MasterRepository masterRepository) {
        this.masterRepository = masterRepository;
    }

    @Transactional
    public Master registerUser(Master master) throws IOException, InvocationTargetException, IllegalAccessException {
        if (!master.getFile().isEmpty()) {
            String path = ResourceUtils.getFile("classpath:static/assets/usercover/").getAbsolutePath();
            byte[] bytes = master.getFile().getBytes();
            String name = UUID.randomUUID() + "." + Objects.requireNonNull(master.getFile().getContentType()).split("/")[1];
            Files.write(Paths.get(path + File.separator + name), bytes);
            master.setCover(name);
        }
        return this.masterRepository.save(master);
    }

    public List<Master> findAllUsers() {
        return this.masterRepository.findAll();
    }

    public  Master findByemail(String email){
        return  masterRepository.findByEmail(email);
    }

    public  Master deleteByemail(String email){
        return  masterRepository.deleteByEmail(email);
    }

    public  Master saveUser(Master master){
       return masterRepository.save(master);
    }

    public  Master findById(int id){
        return  masterRepository.findById(id);
    }

}
