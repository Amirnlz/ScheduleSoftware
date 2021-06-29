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
import java.util.ArrayList;
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

    public User registerUser(User user) throws IOException, InvocationTargetException, IllegalAccessException {
        if (!user.getFile().isEmpty()) {
            String path = ResourceUtils.getFile("classpath:static/assets/usercover/").getAbsolutePath();
            byte[] bytes = user.getFile().getBytes();
            String name = UUID.randomUUID() + "." + Objects.requireNonNull(user.getFile().getContentType()).split("/")[1];
            Files.write(Paths.get(path + File.separator + name), bytes);
            user.setCover(name);
        }

//        if (!master.getPassword().isEmpty())
//            master.setPassword(new BCryptPasswordEncoder().encode(master.getPassword()));

        return this.userRepository.save(user);
    }

    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    public void deleteById(String id){
        userRepository.deleteById(id);
    }

    public User findByemail(String email){
        return  userRepository.findByEmail(email);
    }

    public User deleteByemail(String email){
        return  userRepository.deleteByEmail(email);
    }

    /* used for update User in rest-controller */
    public User saveUser(User user){
       return userRepository.save(user);
    }

    public  List<User>findByRoles(String role){
        List<User>temp=new ArrayList<>();
        for (User user:userRepository.findAll()){
            if(user.getRoles().get(0).equals(role)){
                temp.add(user);
            }
        }
        return temp;
    }



}
