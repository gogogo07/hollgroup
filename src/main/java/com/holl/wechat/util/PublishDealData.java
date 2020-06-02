package com.holl.wechat.util;

import com.holl.wechat.model.Deal;

public class PublishDealData {
    public int id;
    public Long orderId;
    public String type;
    public String title;
    public String url;
    public String idurl;
    public String idname;
    public String phone;
    public float price;

    public PublishDealData(int id, Deal deal, String url) {
        this.id = id;
        this.orderId = deal.getOrderId();
        this.type = deal.getOrder().getType();
        this.title = deal.getOrder().getTitle();
        this.idurl = deal.getFrom().getAvatarUrl();
        this.url = "http://localhost:3434/image/download?fileName=" + url; 
        this.idname = deal.getFrom().getName();
        this.phone = deal.getOrder().getPhone();
        this.price = deal.getOrder().getMoney();
    }
}