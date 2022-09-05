package com.vnpt.controller;

import com.vnpt.common.IBaseService;
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
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ORDER_READ')")
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
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ORDER_READ')")
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
    @PreAuthorize("isAuthenticated() and hasRole('ROLE_ORDER_DELETE')")
    public void deleteOrderById(@PathVariable(name = "id") long id) {
        orderService.deleteById(id);
    }
}
