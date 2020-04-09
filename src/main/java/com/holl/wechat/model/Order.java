package com.holl.wechat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

@Entity(name = "orders")
public class Order {

    @Id
    @Column(nullable = false, length = 30)
    private String id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false)
    private String detail;

    @Column(nullable = false, length = 50)
    private String location;

    @Column(nullable = false)
    private Float money;

    private String remarks;

    @Column()
    private Timestamp publishTime;

    @Column()
    private Timestamp startTime;

    @Column()
    private  Timestamp finishTime;

    public Order() {
        this.id = new Timestamp(System.currentTimeMillis()).toString();
    }

    public Order(String id, String title, String detail,
                 String location, Float money, String remarks,
                 Timestamp publishTime, Timestamp startTime, Timestamp finishTime) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.location = location;
        this.money = money;
        this.remarks = remarks;
        this.publishTime = publishTime;
        this.startTime = startTime;
        this.finishTime = finishTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public void setDetail(String event) {
        this.detail = event;
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Timestamp getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Timestamp publishTime) {
        this.publishTime = publishTime;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }
}
