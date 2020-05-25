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

    public SaleData(int id, Sale sale) {
        this.id = id;
        this.orderId = sale.getId();
        this.type = "3";
        this.title = sale.getTitle();
        this.idurl = sale.getUser().getAvatarUrl();
        this.url = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1856756895,1265182977&fm=26&gp=0.jpg";
        this.idname = sale.getUser().getName();
        this.phone = sale.getPhone();
        this.price = sale.getMoney();
    }
}