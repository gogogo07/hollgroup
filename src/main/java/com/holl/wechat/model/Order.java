package com.holl.wechat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity(name = "orders")
public class Order {

    @Id
    @Column(nullable = false, length = 30)
    private String id;

    @Column(nullable = false, length = 30)
    private String title;

    @Column(nullable = false, length = 50)
    private String event;

    @Column(nullable = false, length = 50)
    private String location;

    @Column(nullable = false)
    private Float money;

    private String remarks;

    @Column()
    private Date publishTime;

    @Column()
    private Date startTime;

    @Column()
    private  Date finishTime;

    public Order() { }

    public Order(String id, String title, String event,
                 String location, Float money, String remarks,
                 Date publishTime, Date startTime, Date finishTime) {
        this.id = id;
        this.title = title;
        this.event = event;
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

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
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

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }
}
