package com.systemlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systemlab.domain.laboratory.Laboratory;

public interface LaboratoryRepository extends JpaRepository<Laboratory, Long>{

}
