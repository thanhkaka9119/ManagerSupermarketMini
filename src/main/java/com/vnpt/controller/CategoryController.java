package com.vnpt.controller;

import com.vnpt.model.Category;
import com.vnpt.common.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
public class CategoryController {

    @Autowired
    private IBaseService categoryService;

    @GetMapping("/categories")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getTypeProductList() {
        Map<String, Object> response = new HashMap<>();
        response.put("data", categoryService.getList());
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }

    @GetMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getTypeProductById(@PathVariable(name = "id") long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", categoryService.getById(id));
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> saveTypeProduct(@RequestBody Category typeProduct) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", categoryService.save(typeProduct));
        response.put("message", "success");
        response.put("status", 201);
        return response;
    }

    @PatchMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> updateTypeProductById(@PathVariable(name = "id") long id, @RequestBody Category typeProduct) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", categoryService.updateById(id, typeProduct));
        response.put("message", "success");
        response.put("status", 201);
        return response;
    }

    @DeleteMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTypeProductById(@PathVariable(name = "id") long id) {
        categoryService.deleteById(id);
    }

}
