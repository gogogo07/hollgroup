package com.holl.wechat.util;

import com.holl.wechat.model.Sale;

public class SaleData {
    public int id;
    public Long orderId;
    public String type;
    public String title;
    public String url;
    public String idurl;
    public String idname;
    public String phone;
    public float price;

    public SaleData(int id, Sale sale, String url) {
        this.id = id;
        this.orderId = sale.getId();
        this.type = "3";
        this.title = sale.getTitle();
        this.idurl = sale.getUser().getAvatarUrl();
        this.url = "http://localhost:3434/sale/download?fileName=" + url;
        this.idname = sale.getUser().getName();
        this.phone = sale.getPhone();
        this.price = sale.getMoney();
    }
}