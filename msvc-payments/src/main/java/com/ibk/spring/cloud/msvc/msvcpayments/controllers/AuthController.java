package com.ibk.spring.cloud.msvc.msvcpayments.controllers;

import com.ibk.spring.cloud.msvc.msvcpayments.clients.config.JwtUtil;
import com.ibk.spring.cloud.msvc.msvcpayments.models.dto.LoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken login = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication authentication = this.authenticationManager.authenticate(login);
        System.out.println(authentication.isAuthenticated());
        System.out.println(authentication.getPrincipal());
        String jwt = this.jwtUtil.create(loginDTO.getUsername());
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();


    }
}
