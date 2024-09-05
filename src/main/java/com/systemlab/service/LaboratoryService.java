package com.systemlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.systemlab.domain.laboratory.Laboratory;
import com.systemlab.domain.laboratory.dto.LaboratoryRequestDTO;
import com.systemlab.repositories.LaboratoryRepository;

@Service
public class LaboratoryService {

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    public Laboratory createLaboratory(LaboratoryRequestDTO data){
        Laboratory laboratory = new Laboratory(data.name(), data.description());
        return laboratoryRepository.save(laboratory); 
    }

    public List<Laboratory> listLaboratories(){
        return laboratoryRepository.findAll(); 
    }
}
