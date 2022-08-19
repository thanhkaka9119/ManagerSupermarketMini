package com.vnpt.controller;

import com.vnpt.model.Product;
import com.vnpt.common.IBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    IBaseService productService;

    @RequestMapping(path = {"/products/page"},method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Map<String,Object> getProductListByPageNumber(@RequestParam(name = "page")int page,
                                                     @RequestParam(name = "per_page")int perPage){
        Map<String, Object> response = new HashMap<>();
        response.put("data","");
        response.put("message","success");
        response.put("status",200);
        return response;
    }

    @RequestMapping(path = {"/products"},method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public Map<String,Object> getProductList(){
        Map<String, Object> response = new HashMap<>();
        response.put("data",productService.getList());
        response.put("message","success");
        response.put("status",200);
        return response;
    }


    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String,Object> getProductById(@PathVariable(name = "id")long id){
        Map<String, Object> response = new HashMap<>();
        response.put("data",productService.getById(id));
        response.put("message","success");
        response.put("status",200);
        return response;
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String,Object> saveProduct(@RequestBody Product productNew){
        Map<String, Object> response = new HashMap<>();
        response.put("data",productService.save(productNew));
        response.put("message","success");
        response.put("status",201);
        return response;
    }

    @RequestMapping(path = "/products/{id}", method = RequestMethod.PATCH)
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String,Object> updateProductById(@PathVariable(name = "id")long id, @RequestBody Product product){
        Map<String, Object> response = new HashMap<>();
        response.put("data",productService.updateById(id,product));
        response.put("message","success");
        response.put("status",201);
        return response;
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable(name = "id")long id){
        productService.deleteById(id);
    }

}
