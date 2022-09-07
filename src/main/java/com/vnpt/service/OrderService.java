package com.vnpt.service;

import com.vnpt.common.IBaseService;
import com.vnpt.data_access.IOrderRepository;
import com.vnpt.dto.request.OrderDetailForm;
import com.vnpt.dto.request.OrderForm;
import com.vnpt.dto.response.PaginationData;
import com.vnpt.exception.DuplicateRecordException;
import com.vnpt.exception.ServerErrorException;
import com.vnpt.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
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
    public Order save(Order order) {
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

    public List<Map<String,Object>> getOrderDetailById(long orderId){
        try {
           return orderRepository.getOrderDetailByOrderId(orderId);
        } catch (Exception ex) {
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    @Transactional
    public Order saveOrderAndOrderDetail(OrderForm orderForm){
        int existCode = orderRepository.existByCode((String)orderForm.getCode());
        if(existCode > 0) throw new DuplicateRecordException("mã đơn hàng đã tồn tại");
        try {
            orderRepository.saveOrder(new Date(),orderForm.getUserId(),orderForm.getTotalMoney(),orderForm.getCode());
            long orderId = orderRepository.getOrderIdByCode(orderForm.getCode());
            for(OrderDetailForm item:orderForm.getOrderDetailForms()){
                orderRepository.saveOrderDetail(orderId,item.getProductId(),item.getImport_price(),Integer.parseInt(item.getQuantityPurchased()),item.getPrice());
            }
            Order order = orderRepository.getByOrderId(orderId);
            return order;
        } catch (Exception ex) {
            System.out.println(ex);
            throw new ServerErrorException("lỗi rồi!");
        }
    }

    @Transactional
    public Order updateOrderAndOrderDetail(OrderForm orderForm,long orderId){
    try{
        orderRepository.deleteOrderDetailByOrderId(orderId);
        for(OrderDetailForm item:orderForm.getOrderDetailForms()){
//            int quantity = orderRepository.getQuantityByProductId(item.getProductId());
//            int quantityRemaining = quantity - Integer.parseInt(item.getQuantityPurchased());
//            orderRepository.updateProductQuantity(item.getProductId(),quantityRemaining);
            orderRepository.saveOrderDetail(orderId,item.getProductId(),item.getImport_price(),Integer.parseInt(item.getQuantityPurchased()),item.getPrice());
        }
        orderRepository.updateTotalMoney(orderId,orderForm.getTotalMoney());
        Order order = orderRepository.getByOrderId(orderId);
        return order;
        } catch (Exception ex) {
            System.out.println(ex);
            throw new ServerErrorException("lỗi rồi!");
        }
    }

}
