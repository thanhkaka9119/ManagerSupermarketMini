package com.vnpt.service;

import com.vnpt.common.IBaseService;
import com.vnpt.data_access.IOrderRepository;
import com.vnpt.dto.response.PaginationData;
import com.vnpt.exception.DuplicateRecordException;
import com.vnpt.exception.ServerErrorException;
import com.vnpt.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderService implements IBaseService<Order,Long> {
    @Autowired
    private IOrderRepository orderRepository;

    public PaginationData getFollowPage(int page, int per_page) {
        try {
            PaginationData responseData = new PaginationData();
            int totalRecord = orderRepository.getTotalOrders();
            int start = (page-1)*per_page;
            List<Map<String,Object>> orders = orderRepository.getOrderByNumberPage(start,per_page);
            responseData.setContent(orders);
            responseData.setTotalCount(totalRecord);
            return responseData;
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    public PaginationData filterOrders(String searchString, int page, int per_page) {
        try {
            PaginationData responseData = new PaginationData();
            String keyword ='%' + searchString + '%';
            int totalRecord = orderRepository.getTotalOrderSearch(keyword);
            int start = (page-1)*per_page;
            List<Map<String,Object>> orders = orderRepository.findByCodeAndPagination(keyword,start,per_page);
            responseData.setContent(orders);
            responseData.setTotalCount(totalRecord);
            return responseData;
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    @Override
    public List<Order> getList() {
        return null;
    }

    @Override
    public Order getById(Long id) {
        return null;
    }

    @Override
    public Order updateById(Long id, Order model) {
        return null;
    }

    @Override
    public Order save(Order model) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
//        int orderAmount = orderRepository.existIdInOrderDetail(id);
//        if(orderAmount > 0) throw new DuplicateRecordException("Sản phẩm có hoá đơn liên quan, không thể xoá!");
        try {
            orderRepository.deleteOrderDetailByOrderId(id);
            orderRepository.deleteOrderById(id);
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }
}
