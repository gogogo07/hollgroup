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
    public float price;

    public PublishDealData(int id, Deal deal) {
        this.id = id;
        this.orderId = deal.getOrderId();
        this.type = deal.getOrder().getType();
        this.title = deal.getOrder().getTitle();
        this.idurl = deal.getFrom().getAvatarUrl();
        this.url = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1856756895,1265182977&fm=26&gp=0.jpg";
        this.idname = deal.getFrom().getName();
        this.price = deal.getOrder().getMoney();
    }
}