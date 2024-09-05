package com.systemlab.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.systemlab.domain.session.dto.SessionResponseDTO;
import com.systemlab.service.SessionService;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired
    private SessionService sessionService;

    @GetMapping("/sessions")
    public ResponseEntity<List<SessionResponseDTO>> listSessions(){
        return ResponseEntity.ok(this.sessionService.listSessions());
    }

    @GetMapping("/sessions/{laboratoryId}")
    public ResponseEntity<List<SessionResponseDTO>> listSessionsByLaboratory(@PathVariable Long laboratoryId){
        return ResponseEntity.ok(this.sessionService.listSessionsbyLaboratory(laboratoryId));
    }
}
