package com.vnpt.controller;

import com.vnpt.model.Product;
import com.vnpt.common.IBaseService;
import com.vnpt.service.ProductService;
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

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getProductList() {
        Map<String, Object> response = new HashMap<>();
        response.put("data", productService.getList());
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }


    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getProductById(@PathVariable(name = "id") long id) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", productService.getById(id));
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> saveProduct(@RequestBody Product productNew) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", productService.save(productNew));
        response.put("message", "success");
        response.put("status", 201);
        return response;
    }

    @PatchMapping("/products/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Map<String, Object> updateProductById(@PathVariable(name = "id") long id, @RequestBody Product product) {
        Map<String, Object> response = new HashMap<>();
        response.put("data", productService.updateById(id, product));
        response.put("message", "success");
        response.put("status", 201);
        return response;
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable(name = "id") long id) {
        productService.deleteById(id);
    }

    @GetMapping(path = {"/products/page"})
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> getByPageNumber(@RequestParam(name = "page", defaultValue = "0") int page,
                                               @RequestParam(name = "per_page", defaultValue = "10") int per_page) {
        Map<String, Object> response = new HashMap<>();
        ProductService productServiceMore = (ProductService) productService;
        response.put("data", productServiceMore.getFollowPage(page, per_page));
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }

    @GetMapping("/products/search")
    @ResponseStatus(HttpStatus.OK)
    public Map<String, Object> readProductsWithFilter(@RequestParam(name = "query") String query,
                                                      @RequestParam(name = "page") int page,
                                                      @RequestParam(name = "per_page") int per_page) {
        Map<String, Object> response = new HashMap<>();
        ProductService productServiceMore = (ProductService) productService;
        response.put("data", productServiceMore.filterProducts(query, page, per_page));
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }

    @GetMapping("/products/sort")
    public Map<String, Object> sortProductWithPrice(@RequestParam(name = "sortby") String sortby,
                                                    @RequestParam(name = "order") String order,
                                                    @RequestParam(name = "page") int page,
                                                    @RequestParam(name = "per_page") int per_page) {
        Map<String, Object> response = new HashMap<>();
        ProductService productServiceMore = (ProductService) productService;
        response.put("data", productServiceMore.sortProducts(sortby, order, page, per_page));
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }
}
