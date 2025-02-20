package com.finalproject.schedule.Modules.User.controller;

import com.finalproject.schedule.Modules.Announcements.model.Announce;
import com.finalproject.schedule.Modules.Announcements.service.AnnounceService;
import com.finalproject.schedule.enums.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import com.finalproject.schedule.Modules.User.service.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.finalproject.schedule.Modules.User.model.User;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/Users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String user(Model model, Principal principal, @PageableDefault(size = 20) Pageable pageable){
        model.addAttribute("new_user", new User()); /* used in form to add new User */
        model.addAttribute("user_model", userService.findAllUsers(pageable)); /* used in table to to show User Information */
        model.addAttribute("profile", userService.findByEmail(principal.getName())); /* used in navbar to to show User Profile */
        return "admin/admin_user";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute User user) throws IOException, InvocationTargetException, IllegalAccessException {
        userService.registerUser(user);
        return "redirect:/Users";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/Users";
    }

}
