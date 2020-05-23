package com.holl.wechat.util;

import java.util.ArrayList;
import java.util.List;

import com.holl.wechat.model.Deal;
import com.holl.wechat.model.Image;

public class DealDetail {

    private final String baseUrl = "http://localhost:3434/image/download?fileName=";

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
    public String url;
    public List<String> imgList = new ArrayList<>();
    public Long orderId;
    public String orderType;

    public DealDetail(Deal deal, List<Image> images) {
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

        if (images.size() > 0) {
            for (Image image: images) {
                imgList.add(baseUrl + image.getImageName());
            }
        } else {
            imgList.add(baseUrl + "0000.jpg");
        }
        this.url = imgList.get(0);

        if (deal.getTo() != null) {
            this.toName = deal.getTo().getName();
        } else {
            this.toName = null;
        }
    }
}