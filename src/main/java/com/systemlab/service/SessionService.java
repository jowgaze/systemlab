package com.systemlab.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.systemlab.domain.laboratory.Laboratory;
import com.systemlab.domain.session.Session;
import com.systemlab.domain.session.ShiftSession;
import com.systemlab.domain.session.dto.SessionResponseDTO;
import com.systemlab.domain.supervisor.Supervisor;
import com.systemlab.repositories.LaboratoryRepository;
import com.systemlab.repositories.SessionRepository;
import com.systemlab.repositories.SupervisorRepository;

@Service
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private SupervisorRepository supervisorRepository;

    @Autowired
    private LaboratoryRepository laboratoryRepository;

    public SessionResponseDTO createSession(Long laboratory_id, Long supervisor_id, ShiftSession shift) {
        Laboratory laboratory = laboratoryRepository.findById(laboratory_id)
                .orElseThrow(() -> new IllegalArgumentException("Laboratory not found"));

        Supervisor supervisor = supervisorRepository.findById(supervisor_id)
                .orElseThrow(() -> new IllegalArgumentException("Supervisor not found"));

        if (sessionRepository.findBySupervisor(supervisor) != null)
            return null;

        Session session = sessionRepository.save(new Session(shift, laboratory, supervisor));

        return this.getSessionResponse(session);
    }

    public List<SessionResponseDTO> listSessions() {
        List<SessionResponseDTO> listResponse = new ArrayList<>();

        this.sessionRepository.findAll().forEach(session -> {
            listResponse.add(this.getSessionResponse(session));
        });
        return listResponse;
    }

    public List<SessionResponseDTO> listSessionsbyLaboratory(Long laboratory_id) {
        List<SessionResponseDTO> listResponse = new ArrayList<>();
        List<Session> sessions = sessionRepository.findByLaboratory(laboratoryRepository.findById(laboratory_id)
        .orElseThrow(() -> new IllegalArgumentException("Laboratory not found")));

        sessions.forEach(session -> {
            listResponse.add(this.getSessionResponse(session));
        });

        return listResponse;
    }

    public Boolean deleteSession(Long session_id) {
        Session session = this.sessionRepository.findById(session_id)
        .orElseThrow(() -> new IllegalArgumentException("Session not found"));
        
        if(session.getStatus())
        return false;
        
        this.sessionRepository.delete(session);
        return true;
    }

    public SessionResponseDTO changeStatusSession(String username) {
        Long supervisorId = supervisorRepository.findByUsername(username).getId();

        Session session = sessionRepository.findBySupervisor(supervisorRepository.findById(supervisorId)
                .orElseThrow(() -> new IllegalArgumentException("Supervisor not found")));

        if(session != null)
            session.setStatus(!session.getStatus());
        else
            return null;

        return this.getSessionResponse(sessionRepository.save(session));
    }

    private SessionResponseDTO getSessionResponse(Session session) {
        return new SessionResponseDTO(
                session.getId(),
                session.getStatus(),
                session.getShift(),
                session.getLaboratory().getId(),
                session.getLaboratory().getName(),
                session.getSupervisor().getId(),
                session.getSupervisor().getName());
    }
}
