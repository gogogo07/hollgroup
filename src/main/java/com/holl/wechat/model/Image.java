package com.holl.wechat.model;

public class Image {
    private Long orderId;
    private String imageName;

    public Image() {}

    public Image(Long orderId, String imageName) {
        this.orderId = orderId;
        this.imageName = imageName;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return "Image [imageName=" + imageName + ", orderId=" + orderId + "]";
    }

    
}