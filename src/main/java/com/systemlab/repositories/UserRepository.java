package com.systemlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemlab.domain.user.StatusUser;
import com.systemlab.domain.user.User;
import java.util.List;


public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    
    List<User> findByStatus(StatusUser status);
}
