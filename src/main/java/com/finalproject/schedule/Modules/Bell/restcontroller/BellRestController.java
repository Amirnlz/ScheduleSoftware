package com.finalproject.schedule.Modules.Bell.restcontroller;

import com.finalproject.schedule.Modules.Bell.model.Bell;
import com.finalproject.schedule.Modules.Bell.service.BellService;
import com.finalproject.schedule.Modules.Day.model.Day;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Bells")
public class BellRestController {

    private BellService bellService;

    @Autowired
    public BellRestController(BellService bellService) {
        this.bellService = bellService;
    }

    /*
    http://localhost:8085/api/Bells
    POST
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public Bell addBell(@RequestBody Bell bell) {
        return bellService.addBell(bell);
    }

    /*
    http://localhost:8085/api/Bells
    GET
    */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Bell> getBells() {
        return bellService.findAllBells();
    }

    /*
    http://localhost:8085/api/Bells/{id}
    GET
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> findDayById(@PathVariable("id")int id){
        Optional<Bell> foundedBell= Optional.ofNullable(bellService.findById(id));
        return foundedBell.map(response-> ResponseEntity.ok().body(response)).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /*
    http://localhost:8085/api/Bells/{id}
    PUT
    */
    @DeleteMapping("/{id}")
    public ResponseEntity<Bell> deletebyId(@PathVariable int id){
        bellService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    /*
    http://localhost:8085/api/Bells/{id}
    DELETE
    */
    @PutMapping(value = "/{id}")
    public ResponseEntity<Bell>update(@RequestBody Bell  bell){
        Bell upBell=bellService.addBell(bell);
        return  ResponseEntity.ok().body(upBell);
    }

}

