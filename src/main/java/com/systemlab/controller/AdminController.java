package com.systemlab.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.systemlab.domain.laboratory.Laboratory;
import com.systemlab.domain.laboratory.dto.LaboratoryRequestDTO;
import com.systemlab.domain.session.ShiftSession;
import com.systemlab.domain.session.dto.SessionResponseDTO;
import com.systemlab.domain.supervisor.dto.SupervisorResponseDTO;
import com.systemlab.domain.user.StatusUser;
import com.systemlab.service.LaboratoryService;
import com.systemlab.service.SessionService;
import com.systemlab.service.SupervisorService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private SupervisorService supervisorService;

    @Autowired
    private LaboratoryService laboratoryService;

    @Autowired
    private SessionService sessionService;

    @PutMapping("/users/activateSupervisor/{username}")
    public ResponseEntity<SupervisorResponseDTO> activateSupervisor(@PathVariable String username) {
        SupervisorResponseDTO response = supervisorService.activateSupervisor(username);
        if (response != null)
            return ResponseEntity.ok(response);
        else
            return ResponseEntity.badRequest().build();
    }

    @PutMapping("/users/disableSupervisor/{username}")
    public ResponseEntity<SupervisorResponseDTO> disableSupervisor(@PathVariable String username) {
        SupervisorResponseDTO response = supervisorService.disableSupervisor(username);
        if (response != null)
            return ResponseEntity.ok(response);
        else
            return ResponseEntity.badRequest().build();
    }

    @PutMapping("/users/requestReactivateSupervisor/{registration}")
    public ResponseEntity<SupervisorResponseDTO> requestReactivateSupervisor(@PathVariable String registration) {
        SupervisorResponseDTO response = supervisorService.requestReactivate(registration);
        if (response != null)
            return ResponseEntity.ok(response);
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/users/checked")
    public ResponseEntity<List<SupervisorResponseDTO>> listCheckedUsers() {
        List<SupervisorResponseDTO> list = this.supervisorService.filterSupervisorsByStatus(StatusUser.CHECKED);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/users/unchecked")
    public ResponseEntity<List<SupervisorResponseDTO>> listUncheckedUsers() {
        List<SupervisorResponseDTO> list = this.supervisorService.filterSupervisorsByStatus(StatusUser.UNCHECKED);

        return ResponseEntity.ok(list);
    }

    @GetMapping("/users/disable")
    public ResponseEntity<List<SupervisorResponseDTO>> listDisableUsers() {
        List<SupervisorResponseDTO> list = this.supervisorService.filterSupervisorsByStatus(StatusUser.DISABLED);

        return ResponseEntity.ok(list);
    }

    @PostMapping("/laboratory/create")
    public ResponseEntity<Laboratory> createLaboratory(@RequestBody LaboratoryRequestDTO body) {
        Laboratory laboratory = this.laboratoryService.createLaboratory(body);

        return ResponseEntity.ok(laboratory);
    }

    @GetMapping("/laboratory/list")
    public ResponseEntity<List<Laboratory>> listLaboratories() {
        return ResponseEntity.ok(this.laboratoryService.listLaboratories());
    }

    @PostMapping("/session/createSession/{laboratoryId}/{supervisorId}")
    public ResponseEntity<SessionResponseDTO> createSession(@PathVariable Long laboratoryId,
            @PathVariable Long supervisorId, @RequestBody ShiftSession shift) {
        SessionResponseDTO response = sessionService.createSession(laboratoryId, supervisorId, shift);
        if (response != null)
            return ResponseEntity.ok(response);
        else
            return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/session/deleteSession/{sessionId}")
    public ResponseEntity<Boolean> deleteSession(@PathVariable Long sessionId){
        if(this.sessionService.deleteSession(sessionId))
            return ResponseEntity.ok().build();
        else
            return ResponseEntity.badRequest().build();
    }
}
