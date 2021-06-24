package com.finalproject.schedule.Modules.Day.service;

import com.finalproject.schedule.Modules.Day.model.Day;
import com.finalproject.schedule.Modules.Day.repository.DayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class DayService {

    private DayRepository dayRepository;

    @Autowired
    public DayService(DayRepository dayRepository) {
        this.dayRepository = dayRepository;
    }

    @Transactional
    public Day addDay(Day day) {
        return this.dayRepository.save(day);
    }

    public List<Day> findAllDays() {
        return this.dayRepository.findAll();
    }

}

