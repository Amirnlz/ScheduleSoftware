package com.finalproject.schedule.Modules.Master.repository;

import com.finalproject.schedule.Modules.Master.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends JpaRepository<Master,Long> {

    Master findByEmail(String email);

}
