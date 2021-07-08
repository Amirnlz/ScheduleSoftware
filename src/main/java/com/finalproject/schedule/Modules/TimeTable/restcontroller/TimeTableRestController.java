package com.finalproject.schedule.Modules.TimeTable.restcontroller;

import com.finalproject.schedule.Modules.TimeTable.model.TimeTable;
import com.finalproject.schedule.Modules.TimeTable.service.TimeTableService;
import com.finalproject.schedule.Modules.User.model.PageModel;
import com.finalproject.schedule.Modules.User.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/TimeTables")
public class TimeTableRestController {

    @Autowired
    TimeTableService timeTableService;

    @GetMapping(value = "")
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
            pageModel.setTotalPages(totalList.size()/pageSie);//need to fix
            if(pageSie<=totalList.size())
                pageModel.setList(totalList.subList((pageNumber)*pageSie,(pageNumber+1)*pageSie));
            else
                pageModel.setList(totalList);
            return  pageModel;
        }

        pageModel.setPageSize(pageSie);
        pageModel.setPageNumber(pageNumber);
        pageModel.setTotalPages(total/pageSie);
        pageModel.setList(timeTableService.findPaginated(pageNumber,pageSie));
        return  pageModel;
    }
}
