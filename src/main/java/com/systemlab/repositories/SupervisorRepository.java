package com.systemlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemlab.domain.supervisor.Supervisor;
import com.systemlab.domain.user.StatusUser;
import java.util.List;


public interface SupervisorRepository extends JpaRepository<Supervisor, Long> {
    Supervisor findByUsername(String username);

    Supervisor findByRegistration(String registration);

    List<Supervisor> findByStatus(StatusUser status);
}
