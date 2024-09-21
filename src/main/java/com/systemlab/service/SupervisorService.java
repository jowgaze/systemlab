package com.systemlab.service;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.systemlab.domain.supervisor.Supervisor;
import com.systemlab.domain.supervisor.dto.SupervisorRequestDTO;
import com.systemlab.domain.supervisor.dto.SupervisorResponseDTO;
import com.systemlab.domain.user.Role;
import com.systemlab.domain.user.StatusUser;
import com.systemlab.repositories.SupervisorRepository;

@Service
public class SupervisorService {

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public SupervisorResponseDTO requestRegister(SupervisorRequestDTO data) {
        if (this.supervisorRepository.findByUsername(data.username()) != null
                || this.supervisorRepository.findByRegistration(data.registration()) != null)
            return null;

        Supervisor supervisor = this.supervisorRepository.save(
                new Supervisor(
                        data.username(),
                        this.passwordEncoder.encode(data.password()),
                        data.registration(),
                        data.name(),
                        data.course(),
                        data.entryperiod()));

        return this.getSupervisorResponse(supervisor);
    }

    public SupervisorResponseDTO activateSupervisor(String username) {
        Supervisor supervisor = supervisorRepository.findByUsername(username);
        if (supervisor != null && supervisor.getStatus() == StatusUser.UNCHECKED) {
            supervisor.setStatus(StatusUser.CHECKED);
            supervisor.setRole(Role.SUPERVISOR);
            supervisorRepository.save(supervisor);

            return this.getSupervisorResponse(supervisor);
        }

        return null;
    }

    public SupervisorResponseDTO disableSupervisor(String username) {
        Supervisor supervisor = supervisorRepository.findByUsername(username);
        if (supervisor != null && supervisor.getStatus() != StatusUser.DISABLED) {
            supervisor.setStatus(StatusUser.DISABLED);
            supervisor.setRole(null);
            supervisorRepository.save(supervisor);

            return this.getSupervisorResponse(supervisor);
        }

        return null;
    }

    public SupervisorResponseDTO requestReactivate(String registration) {
        Supervisor supervisor = supervisorRepository.findByRegistration(registration);

        if (supervisor != null && supervisor.getStatus() == StatusUser.DISABLED) {
            supervisor.setStatus(StatusUser.UNCHECKED);
            supervisorRepository.save(supervisor);

            return this.getSupervisorResponse(supervisor);
        }

        return null;
    }

    public List<SupervisorResponseDTO> filterSupervisorsByStatus(StatusUser status){
        List<SupervisorResponseDTO> listResponse = new ArrayList<>();

        this.supervisorRepository.findByStatus(status).forEach(supervisor -> {
            listResponse.add(this.getSupervisorResponse(supervisor));
        });

        return listResponse;
    }

    public SupervisorResponseDTO getByToken(String username){
        return this.getSupervisorResponse(supervisorRepository.findByUsername(username));
    }

    private SupervisorResponseDTO getSupervisorResponse(Supervisor supervisor) {
        return new SupervisorResponseDTO(
                supervisor.getId(),
                supervisor.getUsername(),
                supervisor.getRegistration(),
                supervisor.getName(),
                supervisor.getCourse(),
                supervisor.getEntryperiod(),
                supervisor.getStatus(),
                supervisor.getRole());
    }
}
