package com.finalproject.schedule.Modules.Course.restcontroller;

import com.finalproject.schedule.Modules.Announcements.model.Announce;
import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Course.model.Course;
import com.finalproject.schedule.Modules.Course.service.CourseService;
import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.Master.model.MasterCourse;
import com.finalproject.schedule.Modules.Master.service.MasterCourseService;
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
//@RequestMapping("/api/Courses")
public class CourseRestController {

    private CourseService courseService;
    private MasterCourseService mastercourseService;
    private TimeTableService timetableService;
    private UserService userService;

    @Autowired
    public CourseRestController(CourseService courseService, MasterCourseService mastercourseService, TimeTableService timetableService, UserService userService) {
        this.courseService = courseService;
        this.mastercourseService = mastercourseService;
        this.timetableService = timetableService;
        this.userService = userService;
    }

    @RequestMapping(value = "/api/Courses", method = RequestMethod.POST)
    public Course addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @GetMapping(value = "/api/Courses")
    public PageModel getAnnouncePage(@RequestParam String name, @RequestParam int unitCount, @RequestParam int pageSie, @RequestParam int pageNumber){

        int total=courseService.findAllCourses().size();
        List<Course>foundedByUnitCounts=courseService.findByUnitCounts(unitCount);
        List<Course> foundedByTitle=courseService.findByTitle(name);
        List<Course>totalList=new ArrayList<>();
        totalList.addAll(foundedByUnitCounts);
        totalList.addAll(foundedByTitle);

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
        pageModel.setList(courseService.findPaginated(pageNumber,pageSie));
        return  pageModel;

//        PageModel pageModel=new PageModel();
//        if(foundedByTitle.size()!=0){
//            pageModel.setPageSize(foundedByTitle.size());
//            pageModel.setPageNumber(pageNumber);
//            pageModel.setTotalPages(1);
//            pageModel.setList(foundedByTitle);
//            return  pageModel;
//        }
//        if(!foundedByUnitCounts.isEmpty()){
//            pageModel.setPageSize(pageSie);
//            pageModel.setPageNumber(pageNumber);
//            pageModel.setTotalPages(foundedByUnitCounts.size()/pageSie);
//            pageModel.setList(foundedByUnitCounts);
//            return  pageModel;
//        }
//
//        pageModel.setPageSize(pageSie);
//        pageModel.setPageNumber(pageNumber);
//        pageModel.setTotalPages(total/pageSie);
//        pageModel.setList(courseService.findPaginated(pageNumber,pageSie));
//        return  pageModel;
    }

    @RequestMapping(value = "/api/Courses/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findDayById(@PathVariable("id") int id) {
        Optional<Course> foundedCourse = Optional.ofNullable(courseService.findById(id));
        return foundedCourse.map(response -> ResponseEntity.ok().body(response)).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping(value = "/api/Courses/{id}")
    public ResponseEntity<Course> update(@RequestBody Course course) {
        courseService.addCourse(course);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/api/Courses/{id}")
    public ResponseEntity<Course> deletebyId(@PathVariable int id) {
        courseService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/api/Courses/{id}/TimeTables", method = RequestMethod.GET)
    public List<TimeTable> findTimeTableByCourseNumber(@PathVariable("id") int id) {
        return timetableService.findTimeTable(id);
    }

    @RequestMapping(value = "/api/Courses/{id}/Masters", method = RequestMethod.GET)
    public List<User> findUserByCourseNumber(@PathVariable("id") int id) {
        return mastercourseService.findMaster(id);
    }

    @PostMapping(value = "/api/MasterCourses/{id}/Choose")
    public ResponseEntity setMasterCourse(@PathVariable("id") int coursenumber) {
        Course course = courseService.findById(coursenumber);
        if (course != null) {
            MasterCourse mastercourse = new MasterCourse();
            mastercourse.setCourse(course);
            mastercourse.setUser(userService.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
            mastercourseService.addMasterCourse(mastercourse);
        }

        return ResponseEntity.ok().build();
    }

}



