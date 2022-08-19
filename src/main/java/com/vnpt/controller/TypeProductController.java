package com.vnpt.controller;

import com.vnpt.model.TypeProduct;
import com.vnpt.common.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/v1")
public class TypeProductController {

    @Autowired
    private IBaseService typeProductService;

    @GetMapping("/type-product")
    @ResponseStatus(HttpStatus.OK)
    public Map<String,Object> getTypeProductList(){
        Map<String, Object> response = new HashMap<>();
        response.put("data", typeProductService.getList());
        response.put("message","success");
        response.put("status",200);
        return response;
    }

    @GetMapping("/type-product/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String,Object> getTypeProductById(@PathVariable(name = "id")long id){
        Map<String, Object> response = new HashMap<>();
        response.put("data", typeProductService.getById(id));
        response.put("message","success");
        response.put("status",200);
        return response;
    }

    @PostMapping("/type-product")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> saveTypeProduct(@RequestBody TypeProduct typeProduct){
        Map<String, Object> response = new HashMap<>();
        response.put("data", typeProductService.save(typeProduct));
        response.put("message","success");
        response.put("status",201);
        return response;
    }

    @PatchMapping("/type-product/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> updateTypeProductById(@PathVariable(name = "id")long id, @RequestBody TypeProduct typeProduct){
        Map<String, Object> response = new HashMap<>();
        response.put("data", typeProductService.updateById(id,typeProduct));
        response.put("message","success");
        response.put("status",201);
        return response;
    }

    @DeleteMapping("/type-product/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTypeProductById(@PathVariable(name = "id")long id){
        typeProductService.deleteById(id);
    }

}
