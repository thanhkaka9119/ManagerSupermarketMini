package com.vnpt.controller;

import com.google.gson.Gson;
import com.vnpt.common.IBaseService;
import com.vnpt.model.Category;
import com.vnpt.model.Role;
import com.vnpt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class RoleController {
    @Autowired
    private IBaseService roleService;

    @GetMapping("/roles")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated() and hasRole('ROLES_READ')")
    public Map<String, Object> getList() {
        RoleService roleServiceMore = (RoleService) roleService;
        Map<String, Object> response = new HashMap<>();
        response.put("data", roleServiceMore.getRoleAttachPermissionName());
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }

    @GetMapping("/roles/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated() and hasAnyRole('ROLES_EDIT','ROLES_CREATE')")
    public Map<String, Object> getById(@PathVariable(name = "id") long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", roleService.getById(id));
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }

    @GetMapping("/roles/permissions")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated() and hasRole('ROLES_READ')")
    public Map<String, Object> getListPermission() {
        RoleService roleServiceMore = (RoleService) roleService;
        Map<String, Object> response = new HashMap<>();
        response.put("data", roleServiceMore.getPermissionList());
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }

    @PostMapping("/roles")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated() and hasRole('ROLES_CREATE')")
    public Map<String, Object> save(@RequestBody Map<String,Object> params) {
        Gson gson = new Gson();
        Role role = gson.fromJson(gson.toJson(params), Role.class);
        Map<String, Object> response = new HashMap<>();
        response.put("data", roleService.save(role));
        response.put("message", "success");
        response.put("status", 201);
        return response;
    }

    @PatchMapping("/roles/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated() and hasRole('ROLES_EDIT')")
    public Map<String, Object> updateById(@PathVariable(name = "id") long id,@RequestBody Map<String,Object> params) {
        Gson gson = new Gson();
        Role role = gson.fromJson(gson.toJson(params), Role.class);
        Map<String, Object> response = new HashMap<>();
        response.put("data", roleService.updateById(id,role));
        response.put("message", "success");
        response.put("status", 201);
        return response;
    }

    @DeleteMapping("/roles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated() and hasRole('ROLES_DELETE')")
    public void deleteById(@PathVariable(name = "id") long id) {
        roleService.deleteById(id);
    }

}
