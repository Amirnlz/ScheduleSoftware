package com.finalproject.schedule.Modules.User.restcontroller;

import com.finalproject.schedule.Modules.User.model.User;
import com.finalproject.schedule.Modules.User.service.UserService;
import com.finalproject.schedule.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/Userss",method = RequestMethod.GET)
    public List<User>getStudents(){

        List<User>students=new ArrayList<>();
        List<User> masters= userService.findAllUsers();
        for(User student:masters){
            if(student.getRoles().get(0).equals(Roles.STUDENT)){
                students.add(student);
            }
        }
        return  students;

    }

    @RequestMapping(value = "/Userss/:{id}",method = RequestMethod.GET)
    public Optional<ResponseEntity<User>> findStudent(@PathVariable("id")String id){


        Optional<User> foundedStudent= Optional.ofNullable(userService.findByemail(id));
        if(!foundedStudent.isEmpty()){
            return foundedStudent.map(response->ResponseEntity.ok().body(response));
        }
      return Optional.of(new ResponseEntity<User>(HttpStatus.NOT_FOUND));
    }

}
