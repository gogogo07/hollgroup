package com.holl.wechat.model;

import java.sql.Timestamp;

public class Order {

    private Long id;

    private String title;

    private String detail;

    private String location;

    private Float money;

    private String type;

    private String phone;

    private String picture;

    private String publishTime;

    private String startTime;

    private  String finishTime;

    public Order() { }

    public Order(Long id, String title, String detail,
                 String location, Float money, String type,
                 String publishTime, String startTime, String finishTime) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.location = location;
        this.money = money;
        this.type = type;
        this.publishTime = publishTime;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", location='" + location + '\'' +
                ", money=" + money +
                ", type='" + type + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", startTime='" + startTime + '\'' +
                ", finishTime='" + finishTime + '\'' +
                '}';
    }
}
