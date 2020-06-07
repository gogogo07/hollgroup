package com.holl.wechat.util;

import com.holl.wechat.model.Image;
import com.holl.wechat.model.Sale;

import java.util.ArrayList;
import java.util.List;

public class SaleDetail {
    private final String baseUrl = "http://localhost:3434/sale/download?fileName=";
    public String title;
    public String url;
    public String idname;
    public String phone;
    public float price;
    public String detail;
    public List<String> imgList = new ArrayList<>();

    public SaleDetail(Sale sale,List<Image> images) {
        this.title = sale.getTitle();
        this.url = "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1856756895,1265182977&fm=26&gp=0.jpg";
        this.idname = sale.getUser().getName();
        this.phone = sale.getPhone();
        this.price = sale.getMoney();
        this.detail = sale.getDetail();
        if (images.size() > 0) {
            for (Image image: images) {
                imgList.add(baseUrl + image.getImageName());
            }
        } else {
            imgList.add(baseUrl + "404.jpg");
        }
    }
}