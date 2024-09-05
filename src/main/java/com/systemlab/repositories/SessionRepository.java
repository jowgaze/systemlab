package com.systemlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemlab.domain.session.Session;
import java.util.List;
import com.systemlab.domain.supervisor.Supervisor;
import com.systemlab.domain.laboratory.Laboratory;



public interface SessionRepository extends JpaRepository<Session, Long>{
    Session findBySupervisor(Supervisor supervisor);
    List<Session> findByLaboratory(Laboratory laboratory);
}
