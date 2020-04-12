package com.holl.wechat.model;

public class Deal {

    private String fromId;

    private String toId;

    private String orderId;

    private Order order;

    private User from;

    private User to;

    public Deal() { }

    public Deal(String orderId, String fromId, String toId) {
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "fromId='" + fromId + '\'' +
                ", toId='" + toId + '\'' +
                ", orderId='" + orderId + '\'' +
                '}';
    }
}
