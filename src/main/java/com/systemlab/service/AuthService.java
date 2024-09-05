package com.systemlab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.systemlab.config.security.TokenService;
import com.systemlab.domain.user.StatusUser;
import com.systemlab.domain.user.User;
import com.systemlab.domain.user.dto.AuthUserRequestDTO;
import com.systemlab.domain.user.dto.AuthUserResponseDTO;
import com.systemlab.repositories.UserRepository;

@Service
public class AuthService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public AuthUserResponseDTO login(AuthUserRequestDTO data) {
        User user = userRepository.findByUsername(data.username());
        if (user.getStatus() != StatusUser.CHECKED)
            return null;

        var usernamePassword = new UsernamePasswordAuthenticationToken(data.username(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return new AuthUserResponseDTO(token);
    }


}
