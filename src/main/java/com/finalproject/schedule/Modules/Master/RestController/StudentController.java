package com.finalproject.schedule.Modules.Master.RestController;

import com.finalproject.schedule.Modules.Master.model.Master;
import com.finalproject.schedule.Modules.Master.service.MasterService;
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
    MasterService masterService;

    @RequestMapping(value = "/Userss",method = RequestMethod.GET)
    public List<Master>getStudents(){

        List<Master>students=new ArrayList<>();
        List<Master> masters=masterService.findAllUsers();
        for(Master student:masters){
            if(student.getRoles().get(0).equals(Roles.STUDENT)){
                students.add(student);
            }
        }
        return  students;

    }

    @RequestMapping(value = "/Userss/:{id}",method = RequestMethod.GET)
    public Optional<ResponseEntity<Master>> findStudent(@PathVariable("id")String id){


        Optional<Master> foundedStudent= Optional.ofNullable(masterService.findByemail(id));
        if(!foundedStudent.isEmpty()){
            return foundedStudent.map(response->ResponseEntity.ok().body(response));
        }
      return Optional.of(new ResponseEntity<Master>(HttpStatus.NOT_FOUND));
    }

}
