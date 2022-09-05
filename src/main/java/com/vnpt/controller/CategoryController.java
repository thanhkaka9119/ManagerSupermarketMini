package com.vnpt.controller;

import com.google.gson.Gson;
import com.vnpt.exception.NotFoundException;
import com.vnpt.model.Category;
import com.vnpt.common.IBaseService;
import com.vnpt.service.CategoryService;
import com.vnpt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_CATE_READ')")
    public Map<String, Object> getList() {
        Map<String, Object> response = new HashMap<>();
        response.put("data", categoryService.getList());
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }

    @GetMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_CATE_EDIT')")
    public Map<String, Object> getById(@PathVariable(name = "id") long id) {
        Category category = (Category) categoryService.getById(id);
        if(category == null) throw new NotFoundException("id không tồn tại");
        Map<String, Object> response = new HashMap<>();
        response.put("data", category);
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }

    @PostMapping("/categories")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_CATE_CREATE')")
    public Map<String, Object> save(@RequestBody Map<String,Object> params) {
        Gson gson = new Gson();
        Category category = gson.fromJson(gson.toJson(params), Category.class);
//        category.setName(String.valueOf(params.get("name")));
        Map<String, Object> response = new HashMap<>();
        response.put("data", categoryService.save(category));
        response.put("message", "success");
        response.put("status", 201);
        return response;
    }

    @PatchMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_CATE_EDIT')")
    public Map<String, Object> updateById(@PathVariable(name = "id") long id, @RequestBody Map<String,Object> params) {
        Gson gson = new Gson();
        Category category = gson.fromJson(gson.toJson(params), Category.class);

        Map<String, Object> response = new HashMap<>();
        response.put("data", categoryService.updateById(id, category));
        response.put("message", "success");
        response.put("status", 201);
        return response;
    }

    @DeleteMapping("/categories/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_CATE_DELETE')")
    public void deleteById(@PathVariable(name = "id") long id) {
        categoryService.deleteById(id);
    }

    @GetMapping(path = {"/categories/page"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_CATE_READ')")
    public Map<String, Object> getByPageNumber(@RequestParam(name = "page", defaultValue = "0") int page,
                                               @RequestParam(name = "per_page", defaultValue = "10") int per_page) {
        Map<String, Object> response = new HashMap<>();
        CategoryService categoryServiceMore = (CategoryService) categoryService;
        response.put("data", categoryServiceMore.getFollowPage(page, per_page));
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }

    @GetMapping("/categories/search")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_CATE_READ')")
    public Map<String, Object> readCategoryWithFilter(@RequestParam(name = "searchString") String searchString,
                                                      @RequestParam(name = "page") int page,
                                                      @RequestParam(name = "per_page") int per_page) {
        Map<String, Object> response = new HashMap<>();
        CategoryService categoryServiceMore = (CategoryService) categoryService;
        response.put("data", categoryServiceMore.filterCategory(searchString, page, per_page));
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }
}
