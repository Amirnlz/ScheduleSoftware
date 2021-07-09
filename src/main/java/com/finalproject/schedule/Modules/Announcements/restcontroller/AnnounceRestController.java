package com.finalproject.schedule.Modules.Announcements.restcontroller;

import com.finalproject.schedule.Modules.Announcements.model.Announce;
import com.finalproject.schedule.Modules.Announcements.service.AnnounceService;
import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.TimeTable.model.TimeTable;
import com.finalproject.schedule.Modules.TimeTable.service.TimeTableService;
import com.finalproject.schedule.Modules.TimeTableBell.model.TimeTableBell;
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
@RequestMapping("/api/Announcements")
public class AnnounceRestController {

    private AnnounceService announceService;
    private TimeTableService timeTableService;
    private UserService userService;

    @Autowired
    public AnnounceRestController(AnnounceService announceService, TimeTableService timeTableService, UserService userService) {
        this.announceService = announceService;
        this.timeTableService = timeTableService;
        this.userService = userService;
    }

    @PostMapping(value = "")
    public ResponseEntity setAnnouncements(@RequestParam int timeTableId, @RequestParam String message){

        String email=  SecurityContextHolder.getContext().getAuthentication().getName();
        User user=userService.findByEmail(email);
        TimeTable timeTable=timeTableService.findById(timeTableId);

        System.out.println(user.getId()+"     "+timeTable.getUser().getId());
        if(timeTable!=null&&user.getId()==timeTable.getUser().getId()){
            Announce announce=new Announce();
            announce.setMessage(message);
            announce.setTimeTable(timeTable);
            announceService.addAnnounce(announce);
            return  ResponseEntity.ok().build();
        }
        else{
            if(timeTable==null)
                return (ResponseEntity) ResponseEntity.status(HttpStatus.NOT_FOUND);
            else
                return (ResponseEntity) ResponseEntity.status(HttpStatus.UNAUTHORIZED);
        }


    }

    @GetMapping(value = "")
    public PageModel getAnnouncePage( @RequestParam int masterId, @RequestParam int timeTableId, @RequestParam int pageSie,@RequestParam int pageNumber){

        int total=announceService.findAllAnnounce().size();
        List<Announce>foundedByTimeTableId=announceService.findByTimeTableId(timeTableId);
        List<Announce> foundedByMasterId=announceService.findByMasterId(masterId);
        List<Announce>totalList=new ArrayList<>();
        totalList.addAll(foundedByTimeTableId);
        totalList.addAll(foundedByMasterId);

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
        pageModel.setList(announceService.findPaginated(pageNumber,pageSie));
        return  pageModel;

//        PageModel pageModel=new PageModel();
//        if(foundedByMasterId.size()!=0){
//            pageModel.setPageSize(foundedByMasterId.size());
//            pageModel.setPageNumber(pageNumber);
//            pageModel.setTotalPages(1);
//            pageModel.setList(foundedByMasterId);
//            return  pageModel;
//        }
//        if(!foundedByTimeTableId.isEmpty()){
//            pageModel.setPageSize(pageSie);
//            pageModel.setPageNumber(pageNumber);
//            pageModel.setTotalPages(foundedByTimeTableId.size()/pageSie);
//            pageModel.setList(foundedByTimeTableId);
//            return  pageModel;
//        }
//
//        pageModel.setPageSize(pageSie);
//        pageModel.setPageNumber(pageNumber);
//        pageModel.setTotalPages(total/pageSie);
//        pageModel.setList(announceService.findPaginated(pageNumber,pageSie));
//        return  pageModel;
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findAnnounceById(@PathVariable int id){

        Optional<Announce> foundedAnnounce= Optional.ofNullable(announceService.findById(id));
        return foundedAnnounce.map(response-> ResponseEntity.ok().body(response)).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Announce> deletebyId(@PathVariable int id){
        announceService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
