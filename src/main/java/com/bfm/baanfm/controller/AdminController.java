package com.bfm.baanfm.controller;

import com.bfm.baanfm.exception.AppException;
import com.bfm.baanfm.model.Role;
import com.bfm.baanfm.model.RoleName;
import com.bfm.baanfm.model.User;
import com.bfm.baanfm.payload.ApiResponse;
import com.bfm.baanfm.payload.JwtAuthenticationResponse;
import com.bfm.baanfm.payload.LoginRequest;
import com.bfm.baanfm.payload.SignUpRequest;
import com.bfm.baanfm.repository.RoleRepository;
import com.bfm.baanfm.repository.UserRepository;
import com.bfm.baanfm.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.security.access.prepost.PreAuthorize;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

@RestController
@RequestMapping("/api/admin")

public class AdminController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

}
