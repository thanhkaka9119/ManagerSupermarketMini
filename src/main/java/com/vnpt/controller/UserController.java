package com.vnpt.controller;

import com.vnpt.model.TypeUser;
import com.vnpt.model.User;
import com.vnpt.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public Map<String,Object> getUserList(){
        Map<String, Object> response = new HashMap<>();
        response.put("data",userService.getUserList());
        response.put("message","success");
        response.put("status",200);
        return response;
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getUserById(@PathVariable(name="id")long id){
        Map<String, Object> response = new HashMap<>();
        response.put("data",userService.getUserById(id));
        response.put("message","success");
        response.put("status",200);
        return response;
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST , consumes = { "multipart/form-data" })
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object>  saveUser(@ModelAttribute User user){
        Map<String, Object> response = new HashMap<>();
        response.put("data",userService.saveUser(user));
        response.put("message","success");
        response.put("status",201);
        return response;
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.PATCH , consumes = { "multipart/form-data" })
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> updateUserById(@PathVariable(name = "id")long id, @ModelAttribute User user){
        Map<String, Object> response = new HashMap<>();
        response.put("data",userService.updateUserById(id,user));
        response.put("message","success");
        response.put("status",201);
        return response;
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable(name = "id")long id){
        userService.deleteUserById(id);
    }

    @RequestMapping(path = {"/users/page"},method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Map<String,Object> getProductListByPageNumber(@RequestParam(name = "page")int page,
                                                         @RequestParam(name = "per_page")int perPage){
        Map<String, Object> response = new HashMap<>();
        response.put("data",userService.getFollowPage(page,perPage));
        response.put("message","success");
        response.put("status",200);
        return response;
    }
}
