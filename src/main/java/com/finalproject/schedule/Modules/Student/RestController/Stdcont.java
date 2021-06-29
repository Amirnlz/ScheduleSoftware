package com.finalproject.schedule.Modules.Student.RestController;

import com.finalproject.schedule.Modules.Student.model.Student;
import com.finalproject.schedule.Modules.Student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class Stdcont {

    private StudentService studentService;

    @Autowired
    public Stdcont(StudentService studentService) {
        this.studentService = studentService;
    }

    @RequestMapping(value = "/Useres", method = RequestMethod.GET)
    public String showStudents(Model model){

        model.addAttribute("students",studentService.getAllStudents());
        return  "student";
    }


    @RequestMapping(value = "/Useres/:{id}",method = RequestMethod.GET)
    public Student findById(@PathVariable ("id")int id){
        return  studentService.findById(id);
    }


}
