package com.systemlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.systemlab.domain.session.dto.SessionResponseDTO;
import com.systemlab.domain.supervisor.dto.SupervisorResponseDTO;
import com.systemlab.domain.user.User;
import com.systemlab.service.SessionService;
import com.systemlab.service.SupervisorService;

@RestController
@RequestMapping("/api/supervisor")
public class SupervisorController {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private SupervisorService supervisorService;

    @PutMapping("/changeStatusSession")
    public ResponseEntity<SessionResponseDTO> changeStatusSession(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SessionResponseDTO response = this.sessionService.changeStatusSession(user.getUsername());

        if (response != null)
            return ResponseEntity.ok(response);
        else
            return ResponseEntity.badRequest().build();
    }

    @GetMapping("/getByToken")
    public ResponseEntity<SupervisorResponseDTO> getByToken(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SupervisorResponseDTO response = this.supervisorService.getByToken(user.getUsername());

        if (response != null)
            return ResponseEntity.ok(response);
        else
            return ResponseEntity.badRequest().build();
    }
}
