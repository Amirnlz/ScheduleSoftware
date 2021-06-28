package com.finalproject.schedule.Modules.TimeTabelBell.repository;

import com.finalproject.schedule.Modules.TimeTabelBell.model.TimeTableBell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTableBellRepository extends JpaRepository<TimeTableBell,Integer> {
}
