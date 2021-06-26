package com.finalproject.schedule.Modules.User.service;

import com.finalproject.schedule.Modules.User.model.User;
import com.finalproject.schedule.Modules.User.repository.UserRepository;
import com.finalproject.schedule.MyBeanCopy;
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
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User registerUser(User master) throws IOException, InvocationTargetException, IllegalAccessException {
        if (!master.getFile().isEmpty()) {
            String path = ResourceUtils.getFile("classpath:static/assets/usercover/").getAbsolutePath();
            byte[] bytes = master.getFile().getBytes();
            String name = UUID.randomUUID() + "." + Objects.requireNonNull(master.getFile().getContentType()).split("/")[1];
            Files.write(Paths.get(path + File.separator + name), bytes);
            master.setCover(name);
        }

//        if (!master.getPassword().isEmpty())
//            master.setPassword(new BCryptPasswordEncoder().encode(master.getPassword()));


        return this.userRepository.save(master);
    }

    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }


    public User findByCodemelli(String codemelli) {
        return userRepository.getOne(codemelli);
    }

    public void deleteByCodemelli(String codemelli){
        userRepository.deleteByCodemelli(codemelli);
    }





    public User findByemail(String email){
        return  userRepository.findByEmail(email);
    }

    public User deleteByemail(String email){
        return  userRepository.deleteByEmail(email);
    }

    public User saveUser(User master){
       return userRepository.save(master);
    }



}
