package com.vnpt.controller;

import com.vnpt.dto.request.SingInForm;
import com.vnpt.dto.response.JwtResponse;
import com.vnpt.model.User;
import com.vnpt.security.jwt.JwtProvider;
import com.vnpt.security.userpincal.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AuthenController {

    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> login(@RequestBody SingInForm singInForm) {
        Authentication authentication = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(singInForm.getUsername(),singInForm.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.createToken(authentication);

        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

        JwtResponse jwtResponse = new JwtResponse(token,userPrinciple.getUsername(),userPrinciple.getAuthorities());

        Map<String, Object> response = new HashMap<>();
        response.put("data", jwtResponse);
        response.put("message", "success");
        response.put("status", 201);
        return response;
    }

    @GetMapping("/random")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_USER_CREATE')")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> randomStuff(){
        Map<String, Object> response = new HashMap<>();
        response.put("data", "JWT Hợp lệ mới có thể thấy được message 1 này");
        response.put("message", "success");
        response.put("status", 201);
        return response;
    }

    @GetMapping("/review")
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_PROD_CREATE')")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> randommStuff(){
        Map<String, Object> response = new HashMap<>();
        response.put("data", "JWT Hợp lệ mới có thể thấy được message 2 này");
        response.put("message", "success");
        response.put("status", 201);
        return response;
    }
}
