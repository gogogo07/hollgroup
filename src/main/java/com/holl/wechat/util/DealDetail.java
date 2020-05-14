package com.holl.wechat.util;

import com.holl.wechat.model.Deal;

public class DealDetail {
    public String title;
    public String detail;
    public float price;
    public String fromName;
    public String toName;
    public String publishTime;
    public String startTime;
    public String finishTime;
    public String location;
    public String phone;
    public Long orderId;
    public String orderType;

    public DealDetail(Deal deal) {
        this.title = deal.getOrder().getTitle();
        this.detail = deal.getOrder().getDetail();
        this.price = deal.getOrder().getMoney();
        this.fromName = deal.getFrom().getName();
        this.publishTime = deal.getOrder().getPublishTime();
        this.startTime = deal.getOrder().getStartTime();
        this.finishTime = deal.getOrder().getFinishTime();
        this.location = deal.getOrder().getLocation();
        this.phone = deal.getOrder().getPhone();
        this.orderId = deal.getOrderId();
        this.orderType = deal.getOrder().getType();
        if (deal.getTo() != null) {
            this.toName = deal.getTo().getName();
        } else {
            this.toName = null;
        }
    }
}