package com.vnpt.controller;

import com.google.gson.Gson;
import com.vnpt.model.Product;
import com.vnpt.common.IBaseService;
import com.vnpt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private IBaseService productService;

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated() and hasRole('PROD_READ')")
    public Map<String, Object> getProductList() {
        ProductService productServiceMore = (ProductService) productService;
        Map<String, Object> response = new HashMap<>();
        response.put("data", productServiceMore.getAll());
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }


    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated() and hasAnyRole('PROD_EDIT','PROD_CREATE')")
    public Map<String, Object> getProductById(@PathVariable(name = "id") long id) {
        ProductService productServiceMore = (ProductService) productService;
        Map<String, Object> response = new HashMap<>();
        response.put("data", productServiceMore.getByProductId(id));
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated() and hasRole('PROD_CREATE')")
    public Map<String, Object> saveProduct(@RequestBody Map<String,Object> params) {
        Gson gson = new Gson();
        Product product = gson.fromJson(gson.toJson(params), Product.class);
        Map<String, Object> response = new HashMap<>();
        response.put("data", productService.save(product));
        response.put("message", "success");
        response.put("status", 201);
        return response;
    }

    @PatchMapping("/products/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated() and hasRole('PROD_EDIT')")
    public Map<String, Object> updateProductById(@PathVariable(name = "id") long id, @RequestBody Map<String,Object> params) {
        Gson gson = new Gson();
        Product product = gson.fromJson(gson.toJson(params), Product.class);
        Map<String, Object> response = new HashMap<>();
        response.put("data", productService.updateById(id, product));
        response.put("message", "success");
        response.put("status", 201);
        return response;
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated() and hasRole('PROD_DELETE')")
    public void deleteProductById(@PathVariable(name = "id") long id) {
        productService.deleteById(id);
    }

    @GetMapping(path = {"/products/page"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated() and hasRole('PROD_READ')")
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
    @PreAuthorize("isAuthenticated() and hasRole('PROD_READ')")
    public Map<String, Object> readProductsWithFilter(@RequestParam(name = "searchString") String searchString,
                                                      @RequestParam(name = "page") int page,
                                                      @RequestParam(name = "per_page") int per_page) {
        Map<String, Object> response = new HashMap<>();
        ProductService productServiceMore = (ProductService) productService;
        response.put("data", productServiceMore.filterProducts(searchString, page, per_page));
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }

//    @GetMapping("/products/sort")
//    public Map<String, Object> sortProductWithPrice(@RequestParam(name = "sortby") String sortby,
//                                                    @RequestParam(name = "order") String order,
//                                                    @RequestParam(name = "page") int page,
//                                                    @RequestParam(name = "per_page") int per_page) {
//        Map<String, Object> response = new HashMap<>();
//        ProductService productServiceMore = (ProductService) productService;
//        response.put("data", productServiceMore.sortProducts(sortby, order, page, per_page));
//        response.put("message", "success");
//        response.put("status", 200);
//        return response;
//    }
}
