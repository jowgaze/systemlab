package com.systemlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.systemlab.domain.session.dto.SessionResponseDTO;
import com.systemlab.domain.user.User;
import com.systemlab.service.SessionService;

@RestController
@RequestMapping("/api/supervisor")
public class SupervisorController {

    @Autowired
    private SessionService sessionService;

    @PutMapping("/changeStatusSession")
    public ResponseEntity<SessionResponseDTO> changeStatusSession(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SessionResponseDTO response = this.sessionService.changeStatusSession(user.getUsername());

        if (response != null)
            return ResponseEntity.ok(response);
        else
            return ResponseEntity.badRequest().build();
    }
}
