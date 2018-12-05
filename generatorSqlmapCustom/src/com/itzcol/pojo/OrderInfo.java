package com.itzcol.pojo;

import java.util.Date;

public class OrderInfo {
    private Integer orderId;

    private String orderName;

    private Long orderPrice;

    private String orderDesc;

    private Integer userId;

    private Date orderCreatetime;

    private Date orderUpdaetetime;

    private Integer orderIsdelete;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName == null ? null : orderName.trim();
    }

    public Long getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Long orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc == null ? null : orderDesc.trim();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getOrderCreatetime() {
        return orderCreatetime;
    }

    public void setOrderCreatetime(Date orderCreatetime) {
        this.orderCreatetime = orderCreatetime;
    }

    public Date getOrderUpdaetetime() {
        return orderUpdaetetime;
    }

    public void setOrderUpdaetetime(Date orderUpdaetetime) {
        this.orderUpdaetetime = orderUpdaetetime;
    }

    public Integer getOrderIsdelete() {
        return orderIsdelete;
    }

    public void setOrderIsdelete(Integer orderIsdelete) {
        this.orderIsdelete = orderIsdelete;
    }
}