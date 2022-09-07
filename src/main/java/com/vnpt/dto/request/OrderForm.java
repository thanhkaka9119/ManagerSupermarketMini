package com.vnpt.dto.request;

import java.util.List;

public class OrderForm {
    private String code;
    private int totalMoney;
    private long userId;
    List<OrderDetailForm> orderDetailForms;

    public OrderForm(){}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(int totalMoney) {
        this.totalMoney = totalMoney;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public List<OrderDetailForm> getOrderDetailForms() {
        return orderDetailForms;
    }

    public void setOrderDetailForms(List<OrderDetailForm> orderDetailForms) {
        this.orderDetailForms = orderDetailForms;
    }
}
