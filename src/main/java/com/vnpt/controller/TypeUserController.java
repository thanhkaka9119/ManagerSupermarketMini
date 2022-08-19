package com.vnpt.controller;

import com.vnpt.model.TypeUser;
import com.vnpt.service.ITypeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class TypeUserController {
    @Autowired
    private ITypeUserService typeUserService;

    @GetMapping("/type-user")
    @ResponseStatus(HttpStatus.OK)
    public Map<String,Object> getTypeUserList(){
        Map<String, Object> response = new HashMap<>();
        response.put("data", typeUserService.getTypeUserList());
        response.put("message","success");
        response.put("status",200);
        return response;
    }

    @GetMapping("/type-user/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String,Object> getTypeUserById(@PathVariable(name = "id")long id){
        Map<String, Object> response = new HashMap<>();
        response.put("data", typeUserService.getTypeUserById(id));
        response.put("message","success");
        response.put("status",200);
        return response;
    }

    @PostMapping("/type-user")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> saveTypeUser(@RequestBody TypeUser typeUser){
        Map<String, Object> response = new HashMap<>();
        response.put("data", typeUserService.saveTypeUser(typeUser));
        response.put("message","success");
        response.put("status",201);
        return response;
    }

    @PatchMapping("/type-user/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> updateTypeUserById(@PathVariable(name = "id")long id, @RequestBody TypeUser typeUser){
        Map<String, Object> response = new HashMap<>();
        response.put("data", typeUserService.updateTypeUserById(id,typeUser));
        response.put("message","success");
        response.put("status",201);
        return response;
    }

    @DeleteMapping("/type-user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductByIndex(@PathVariable(name = "id")long id){
        typeUserService.deleteTypeUserById(id);
    }
}
