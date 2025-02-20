package com.finalproject.schedule.Modules.TimeTable.restcontroller;

import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Course.model.Course;
import com.finalproject.schedule.Modules.Master.model.MasterCourse;
import com.finalproject.schedule.Modules.Student.model.StudentCourse;
import com.finalproject.schedule.Modules.Student.service.StudentCourseService;
import com.finalproject.schedule.Modules.TimeTable.model.TimeTable;
import com.finalproject.schedule.Modules.TimeTable.service.TimeTableService;
import com.finalproject.schedule.Modules.User.model.PageModel;
import com.finalproject.schedule.Modules.User.model.User;
import com.finalproject.schedule.Modules.User.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class TimeTableRestController {

    @Autowired
    TimeTableService timeTableService;

    @Autowired
    UserService userService;
    @Autowired
    StudentCourseService studentCourseService;

    @GetMapping(value = "/api/TimeTables")
    public PageModel getTimeTablePage(@RequestParam int studentId, @RequestParam int CourseId, @RequestParam int masterId,@RequestParam int pageNumber, @RequestParam int pageSie){

        int total=timeTableService.findAllTimeTables().size();
        List<TimeTable> foundedByStudent=timeTableService.findByStudentId(studentId);
        List<TimeTable> foundedByCourse=timeTableService.findByCourseId(CourseId);
        List<TimeTable> foundedByMaster=timeTableService.findByMasterId(masterId);
        List<TimeTable> totalList=new ArrayList<>();
        totalList.addAll(foundedByStudent);
        totalList.addAll(foundedByCourse);
        totalList.addAll(foundedByMaster);

        for(int i=0;i<totalList.size();i++){
            for(int j=i+1;j<totalList.size();j++)
                if(totalList.get(i).equals(totalList.get(j)))
                    totalList.remove(j);
        }

        PageModel pageModel=new PageModel();

        if(!totalList.isEmpty()){
            pageModel.setPageSize(pageSie);
            pageModel.setPageNumber(pageNumber);
            pageModel.setTotalPages((int) Math.ceil((double) totalList.size()/(double) pageSie));
            if(pageSie<=totalList.size())
                pageModel.setList(totalList.subList((pageNumber)*pageSie,(pageNumber+1)*pageSie));
            else
                pageModel.setList(totalList);
            return  pageModel;
        }

        pageModel.setPageSize(pageSie);
        pageModel.setPageNumber(pageNumber);
        pageModel.setTotalPages((int) Math.ceil((double) total/(double)pageSie));
        pageModel.setList(timeTableService.findPaginated(pageNumber,pageSie));
        return  pageModel;
    }

    @RequestMapping(value = "/api/TimeTables/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findDayById(@RequestParam int id){
        Optional<TimeTable> foundedTimeTable= Optional.ofNullable(timeTableService.findById(id));
        return foundedTimeTable.map(response-> ResponseEntity.ok().body(response)).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/api/TimeTableChoose/{id}/Choose")
    public ResponseEntity setStudentCourse(@PathVariable("id") int id) {

        TimeTable timeTable = timeTableService.findById(id);
        User user = userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setUser(user);
        studentCourse.setTimetable(timeTable);
        studentCourseService.addCourse(studentCourse);
        return ResponseEntity.ok().build();
    }

}
