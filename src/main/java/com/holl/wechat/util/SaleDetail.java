package com.holl.wechat.util;

import com.holl.wechat.model.Sale;

public class SaleDetail {
    public String title;
    public String url;
    public String idname;
    public String phone;
    public float price;
    public String detail;

    public SaleDetail(Sale sale) {
        this.title = sale.getTitle();
        this.url = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1856756895,1265182977&fm=26&gp=0.jpg";
        this.idname = sale.getUser().getName();
        this.phone = sale.getPhone();
        this.price = sale.getMoney();
        this.detail = sale.getDetail();
    }
}