package com.vnpt.controller;

import com.google.gson.Gson;
import com.vnpt.common.IBaseService;
import com.vnpt.dto.request.OrderForm;
import com.vnpt.model.Product;
import com.vnpt.service.OrderService;
import com.vnpt.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class OrderController {
    @Autowired
    private IBaseService orderService;

    @GetMapping(path = {"/orders/page"})
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated() and hasRole('ORDER_READ')")
    public Map<String, Object> getByPageNumber(@RequestParam(name = "page", defaultValue = "0") int page,
                                               @RequestParam(name = "per_page", defaultValue = "10") int per_page) {
        Map<String, Object> response = new HashMap<>();
        OrderService orderServiceMore = (OrderService) orderService;
        response.put("data", orderServiceMore.getFollowPage(page, per_page));
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }

    @GetMapping("/orders/search")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated() and hasRole('ORDER_READ')")
    public Map<String, Object> readOrdersWithFilter(@RequestParam(name = "searchString") String searchString,
                                                      @RequestParam(name = "page") int page,
                                                      @RequestParam(name = "per_page") int per_page) {
        Map<String, Object> response = new HashMap<>();
        OrderService orderServiceMore = (OrderService) orderService;
        response.put("data", orderServiceMore.filterOrders(searchString, page, per_page));
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }

    @DeleteMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated() and hasRole('ORDER_DELETE')")
    public void deleteOrderById(@PathVariable(name = "id") long orderId) {
        orderService.deleteById(orderId);
    }

    @GetMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("isAuthenticated() and hasAnyRole('ORDER_EDIT','ORDER_CREATE')")
    public Map<String, Object> getOrderDetailById(@PathVariable(name = "id") long orderId) {
        Map<String, Object> response = new HashMap<>();
        OrderService orderServiceMore = (OrderService) orderService;
        response.put("data", orderServiceMore.getOrderDetailById(orderId));
        response.put("message", "success");
        response.put("status", 200);
        return response;
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated() and hasRole('ORDER_CREATE')")
    public Map<String, Object> saveOrder(@RequestBody Map<String,Object> params) {
        Gson gson = new Gson();
        OrderForm orderForm = gson.fromJson(gson.toJson(params), OrderForm.class);
        OrderService orderServiceMore = (OrderService) orderService;
        Map<String, Object> response = new HashMap<>();
        response.put("data", orderServiceMore.saveOrderAndOrderDetail(orderForm));
        response.put("message", "success");
        response.put("status", 201);
        return response;
    }

    @PatchMapping ("/orders/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("isAuthenticated() and hasRole('ORDER_EDIT')")
    public Map<String, Object> updateOrder(@RequestBody Map<String,Object> params, @PathVariable(name = "id")long orderId) {
        Gson gson = new Gson();
        OrderForm orderForm = gson.fromJson(gson.toJson(params), OrderForm.class);
        OrderService orderServiceMore = (OrderService) orderService;
        Map<String, Object> response = new HashMap<>();
        response.put("data", orderServiceMore.updateOrderAndOrderDetail(orderForm,orderId));
        response.put("message", "success");
        response.put("status", 201);
        return response;
    }
}
