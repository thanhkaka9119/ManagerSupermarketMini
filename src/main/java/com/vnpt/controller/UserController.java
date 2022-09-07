package com.vnpt.controller;

import com.vnpt.model.User;
import com.vnpt.common.IBaseService;
import com.vnpt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private IBaseService userService;

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated() and hasRole('USER_READ')")
    public Map<String, Object> getUserList() {
        Map<String, Object> response = new HashMap<>();
        response.put("data", userService.getList());
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated() and hasAnyRole('USER_EDIT','USER_CREATE')")
    public Map<String, Object> getUserById(@PathVariable(name = "id") long id) {
        UserService userServiceMore = (UserService) userService;
        Map<String, Object> response = new HashMap<>();
        response.put("data", userServiceMore.findById(id));
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }

    @RequestMapping(path = "/users", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated() and hasRole('USER_CREATE')")
    public Map<String, Object> saveUser(@ModelAttribute User user) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", userService.save(user));
        response.put("message", "success");
        response.put("status", 201);
        return response;
    }

    @RequestMapping(path = "/users/{id}", method = RequestMethod.PATCH, consumes = {"multipart/form-data"})
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated() and hasRole('USER_EDIT')")
    public Map<String, Object> updateUserById(@PathVariable(name = "id") long id, @ModelAttribute User user) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", userService.updateById(id, user));
        response.put("message", "success");
        response.put("status", 201);
        return response;
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated() and hasRole('USER_DELETE')")
    public void deleteUserById(@PathVariable(name = "id") long id) {
        userService.deleteById(id);
    }

    @GetMapping("/users/page")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated() and hasRole('USER_READ')")
    public Map<String, Object> getByPageNumber(@RequestParam(name = "page", defaultValue = "0") int page,
                                               @RequestParam(name = "per_page", defaultValue = "10") int perPage) {
        Map<String, Object> response = new HashMap<>();
        UserService userServiceMore = (UserService) userService;
        response.put("data", userServiceMore.getFollowPage(page, perPage));
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }

    @GetMapping("/users/search")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated() and hasRole('USER_READ')")
    public Map<String, Object> readUsersWithFilter(@RequestParam("searchString") String searchString,
                                                   @RequestParam(name = "page") int page,
                                                   @RequestParam(name = "per_page") int per_page) {
        Map<String, Object> response = new HashMap<>();
        UserService userServiceMore = (UserService) userService;
        response.put("data", userServiceMore.filterUsers(searchString, page, per_page));
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }

}
