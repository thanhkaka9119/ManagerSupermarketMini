package com.vnpt.dto.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtResponse {
    long id;
    private String token;
    private String name;
    private Collection<? extends GrantedAuthority> roles;

    public JwtResponse(){}

    public JwtResponse(long id, String token, String name, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.token = token;
        this.name = name;
        this.roles = roles;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
