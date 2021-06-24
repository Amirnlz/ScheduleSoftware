package com.finalproject.schedule.Modules.Master.repository;

import com.finalproject.schedule.Modules.Master.model.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterRepository extends JpaRepository<Master,String> {

    Master findByEmail(String email);
    Master deleteByEmail(String email);
    Master findById(int id);


}
