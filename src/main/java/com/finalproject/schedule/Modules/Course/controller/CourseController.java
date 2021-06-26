package com.finalproject.schedule.Modules.Course.controller;

import com.finalproject.schedule.Modules.Course.model.Course;
import com.finalproject.schedule.Modules.Course.service.CourseService;
import com.finalproject.schedule.Modules.User.model.User;
import com.finalproject.schedule.Modules.User.service.UserService;
import com.finalproject.schedule.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Courses")
public class CourseController {

    private CourseService courseService;
    private UserService userService;

    @Autowired
    public CourseController(CourseService courseService, UserService userService) {
        this.courseService = courseService;
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String course(Model model) {
        model.addAttribute("course", new Course());//form object - used in form
        model.addAttribute("course_model", courseService.findAllCourses());//show in tabel

        List<User> masterList=new ArrayList<>();
        List<User> temp=userService.findAllUsers();
        for(User user:temp){
            if(user.getRoles().get(0).equals(Roles.MASTER)){
                masterList.add(user);
            }
        }
        model.addAttribute("master_model", masterList);
        return "admin/admin_course";
    }

    @RequestMapping(value = "/addcourse", method = RequestMethod.POST)
    public String addcourse(@ModelAttribute(name = "course") Course course) throws IOException, InvocationTargetException, IllegalAccessException {
        courseService.addCourse(course);
        return "redirect:/Courses";
    }

}
