package com.finalproject.schedule.Modules.TimeTable.controller;

import com.finalproject.schedule.Modules.Course.model.Course;
import com.finalproject.schedule.Modules.Master.model.MasterCourse;
import com.finalproject.schedule.Modules.Master.service.MasterCourseService;
import com.finalproject.schedule.Modules.TimeTable.model.TimeTable;
import com.finalproject.schedule.Modules.TimeTable.service.TimeTableService;
import com.finalproject.schedule.Modules.TimeTableBell.model.TimeTableBell;
import com.finalproject.schedule.Modules.TimeTableBell.service.TimeTableBellService;
import com.finalproject.schedule.Modules.User.model.User;
import com.finalproject.schedule.Modules.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TimeTableController {

    private TimeTableService timetableService;
    private MasterCourseService mastercourseService;
    private UserService userService;
    private TimeTableBellService timetablebellService;

    @Autowired
    public TimeTableController(MasterCourseService mastercourseService, UserService userService, TimeTableBellService timetablebellService, TimeTableService timetableService) {
        this.mastercourseService = mastercourseService;
        this.userService = userService;
        this.timetablebellService = timetablebellService;
        this.timetableService = timetableService;
    }

    @RequestMapping(value = "/master_timetable", method = RequestMethod.GET)
    public String TimeTable(Model model, Principal principal) {
        model.addAttribute("new_timetable",new TimeTable());
        User user = userService.findByEmail(principal.getName());
        List<MasterCourse> mastercourseList = mastercourseService.findAllMasterCourses();
        List<MasterCourse> temp = new ArrayList<>();
        for (MasterCourse mastercourse: mastercourseList){
            if (mastercourse.getUser().equals(user)){
                temp.add(mastercourse);
            }
        }
        model.addAttribute("master_course_model",temp);
        model.addAttribute("timetablebell_model",timetablebellService.findAllTimeTableBell());

        List<TimeTable> timetableList = timetableService.findAllTimeTables();
        List<TimeTable> temp2 = new ArrayList<>();
        for (TimeTable timetable: timetableList){
            if (timetable.getUser().equals(user)){
                temp2.add(timetable);
            }
        }
        model.addAttribute("timetable_model",temp2);
        model.addAttribute("profile", userService.findByEmail(principal.getName()));
        return "master/master_timetable";
    }

    @RequestMapping(value = "/master_timetable/addtimetable", method = RequestMethod.POST)
    public String addtimetable(@ModelAttribute TimeTable timetable, Principal principal) throws IOException, InvocationTargetException, IllegalAccessException {
        timetable.setUser(userService.findByEmail(principal.getName()));
        timetable.setAcceptance(2);
        timetableService.registerTimeTable(timetable);
        return "redirect:/master_timetable";
    }


    @RequestMapping(value = {"/master_timetable/delete/{id}"}, method = RequestMethod.GET)
    public String master_delete(@PathVariable("id") int id) {
        timetableService.deleteById(id);
        return "redirect:/master_timetable";
    }

    @RequestMapping(value = {"/admin_timetable/delete/{id}"}, method = RequestMethod.GET)
    public String admin_delete(@PathVariable("id") int id) {
        timetableService.deleteById(id);
        return "redirect:/admin_timetable";
    }

    @RequestMapping(value = {"/admin_timetable/not_accept/{id}"}, method = RequestMethod.GET)
    public String admin_not_accept(@PathVariable("id") int id) throws IllegalAccessException, IOException, InvocationTargetException {
        TimeTable timetable = timetableService.findById(id);
        timetable.setReason("رد شده توسط ادمین!");
        timetable.setAcceptance(0);
        timetableService.registerTimeTable(timetable);
        return "redirect:/admin_timetable";
    }

    @RequestMapping(value = {"/admin_timetable/accept/{id}"}, method = RequestMethod.GET)
    public String admin_accept(@PathVariable("id") int id) throws IllegalAccessException, IOException, InvocationTargetException {
        TimeTable timetable = timetableService.findById(id);
        timetable.setReason("");
        timetable.setAcceptance(1);
        timetableService.registerTimeTable(timetable);
        return "redirect:/admin_timetable";
    }

    @RequestMapping(value = {"/admin_timetable/wait/{id}"}, method = RequestMethod.GET)
    public String admin_wait(@PathVariable("id") int id) throws IllegalAccessException, IOException, InvocationTargetException {
        TimeTable timetable = timetableService.findById(id);
        timetable.setReason("");
        timetable.setAcceptance(2);
        timetableService.registerTimeTable(timetable);
        return "redirect:/admin_timetable";
    }

    @RequestMapping(value = "/admin_timetable", method = RequestMethod.GET)
    public String admin_timetable(Model model, Principal principal) {
        model.addAttribute("new_classnumber", new TimeTable());
        model.addAttribute("timetable_model", timetableService.findAllTimeTables());
        model.addAttribute("profile", userService.findByEmail(principal.getName()));
        return "admin/admin_timetable";
    }

    @RequestMapping(value = "/admin_timetable/start", method = RequestMethod.POST)
    public String start(@ModelAttribute TimeTable timetable, Principal principal) throws IllegalAccessException, IOException, InvocationTargetException {
        List<TimeTable> timetableList = timetableService.findAllTimeTables();
        for(int i=0; i<timetableList.size() ; i++) {
            timetableList.get(i).setAcceptance(1);
            timetableList.get(i).setReason("");
            timetableService.registerTimeTable(timetableList.get(i));
        }

        for(int i=0; i<timetableList.size() ; i++){
            for (int j=i+1; j<timetableList.size() ; j++){

                //TimeTableBell Motafavet -- Day Barabar -- Bell Motafavet -- Course Barabar
                if(!timetableList.get(i).getTimetablebell().equals(timetableList.get(j).getTimetablebell())){
                    if(timetableList.get(i).getTimetablebell().getDay().equals(timetableList.get(j).getTimetablebell().getDay()) && !timetableList.get(i).getTimetablebell().getBell().equals(timetableList.get(j).getTimetablebell().getBell())){
                        if(timetableList.get(i).getCourse().equals(timetableList.get(j).getCourse())){
                            if(coursesize(timetableList.get(i).getCourse(),timetableList.get(i).getTimetablebell()) >= coursesize(timetableList.get(j).getCourse(),timetableList.get(j).getTimetablebell())){
                                timetableList.get(j).setAcceptance(0);
                                timetableList.get(j).setReason(" * یک درس در دانشگاه در دو زنگ جدا نمی تواند تشکیل شود بنابر بر قوانین سیستم اولویت با "+timetableList.get(i).getUser().getName()+" "+timetableList.get(i).getUser().getLastname()+" با جدول زمانی "+timetableList.get(i).getTimetablebell().getDay().getLabel()+" / "+timetableList.get(i).getTimetablebell().getBell().getLabel()+" است! ");
                                timetableService.registerTimeTable(timetableList.get(j));}
                            else{
                                timetableList.get(i).setAcceptance(0);
                                timetableList.get(i).setReason(" * یک درس در دانشگاه در دو زنگ جدا نمی تواند تشکیل شود بنابر بر قوانین سیستم اولویت با "+timetableList.get(j).getUser().getName()+" "+timetableList.get(j).getUser().getLastname()+" با جدول زمانی "+timetableList.get(j).getTimetablebell().getDay().getLabel()+" / "+timetableList.get(j).getTimetablebell().getBell().getLabel()+" است! ");
                                timetableService.registerTimeTable(timetableList.get(i));
                            }

                        }
                    }
                }
                //TimeTableBell Barabar -- Term Barabar -- Dars Motafavet
               if(timetableList.get(i).getTimetablebell().equals(timetableList.get(j).getTimetablebell())){
                    if(timetableList.get(i).getCourse().getTerm() == timetableList.get(j).getCourse().getTerm() && timetableList.get(i).getCourse().getCourseNumber() != timetableList.get(j).getCourse().getCourseNumber()){
                        if(timetablebellsize(timetableList.get(i).getTimetablebell(),timetableList.get(i).getCourse()) >= timetablebellsize(timetableList.get(j).getTimetablebell(),timetableList.get(j).getCourse())){
                            timetableList.get(j).setAcceptance(0);
                            timetableList.get(j).setReason(" * در یک روز و ساعت دو درس با ترم های برابر نمی تواند تشکیل شود بنابر بر قوانین دانشگاه اولویت با "+timetableList.get(i).getUser().getName()+" "+timetableList.get(i).getUser().getLastname()+" با جدول زمانی "+timetableList.get(i).getTimetablebell().getDay().getLabel()+"/"+timetableList.get(i).getTimetablebell().getBell().getLabel()+" است! ");
                            timetableService.registerTimeTable(timetableList.get(j));
                            }
                        else{
                            timetableList.get(i).setAcceptance(0);
                            timetableList.get(i).setReason(" * در یک روز و ساعت دو درس با ترم های برابر نمی تواند تشکیل شود بنابر بر قوانین دانشگاه اولویت با "+timetableList.get(j).getUser().getName()+" "+timetableList.get(j).getUser().getLastname()+" با جدول زمانی "+timetableList.get(j).getTimetablebell().getDay().getLabel()+"/"+timetableList.get(j).getTimetablebell().getBell().getLabel()+" است! ");
                            timetableService.registerTimeTable(timetableList.get(i));
                            }
                    }
                }
            }
        }
        return "redirect:/admin_timetable";
    }

    public int timetablebellsize(TimeTableBell timetablebell,Course course){
        List<TimeTable> timetableList = timetableService.findAllTimeTables();
        int number = 0;
        for(int i=0; i< timetableList.size() ; i++){
            if(timetableList.get(i).getTimetablebell().equals(timetablebell) && timetableList.get(i).getCourse().equals(course)){
                number++;
            }
        }
        return number;
    }

    public int coursesize(Course course,TimeTableBell timetablebell){
        List<TimeTable> timetableList = timetableService.findAllTimeTables();
        int number = 0;
        for(int i=0; i< timetableList.size() ; i++){
            if(timetableList.get(i).getCourse().equals(course) && timetableList.get(i).getTimetablebell().equals(timetablebell)){
                number++;
            }
        }
        return number;
    }

}
