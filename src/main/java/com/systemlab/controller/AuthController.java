package com.systemlab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.systemlab.domain.supervisor.dto.SupervisorRequestDTO;
import com.systemlab.domain.supervisor.dto.SupervisorResponseDTO;
import com.systemlab.domain.user.dto.AuthUserRequestDTO;
import com.systemlab.domain.user.dto.AuthUserResponseDTO;
import com.systemlab.service.AuthService;
import com.systemlab.service.SupervisorService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private SupervisorService supervisorService;

    @Autowired
    private AuthService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthUserResponseDTO> login(@RequestBody @Valid AuthUserRequestDTO body) {
        AuthUserResponseDTO token = userService.login(body);
        if (token != null)
            return ResponseEntity.ok(token);
        else
            return ResponseEntity.badRequest().build();
    }

    @PostMapping("/requestRegister")
    public ResponseEntity<SupervisorResponseDTO> register(@RequestBody @Valid SupervisorRequestDTO body) {
        SupervisorResponseDTO supervisor = this.supervisorService.requestRegister(body);
        if (supervisor != null)
            return ResponseEntity.ok(supervisor);
        else
            return ResponseEntity.badRequest().build();
    }
}
