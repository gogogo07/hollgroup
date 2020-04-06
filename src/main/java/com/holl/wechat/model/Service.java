package com.holl.wechat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "service")
public class Service {

    @Column(nullable = false, length = 30)
    private String fromId;

    @Column(nullable = false, length = 30)
    private String toId;

    @Id
    @Column(nullable = false, length = 30)
    private String orderId;

    public Service() { }

    public Service(String orderId, String fromId, String toId) {
        this.orderId = orderId;
        this.fromId = fromId;
        this.toId = toId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }
}
