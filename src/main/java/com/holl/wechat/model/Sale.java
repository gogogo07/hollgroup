package com.holl.wechat.model;

//id,openid,title,detail,picture,money
public class Sale {

    private long id = 0;

    private String openId = null;

    private User user = null;

    private String title = null;

    private String detail = null;

    private String picture = null;

    private float money = 0;

    private String phone = null;

    public Sale(){  }

    public Sale(long id, String openId, String title, String detail, String picture, float money,String phone){
        this.id = id;
        this.openId = openId;
        this.title = title;
        this.detail = detail;
        this.picture = picture;
        this.money = money;
        this.phone = phone;
    }

    public void setId(long id){ this.id=id; }

    public long getId(){ return id; }

    public void setOpenId(String openId){ this.openId=openId; }

    public String getOpenId(){ return openId; }

    public void setTitle(String title){ this.title = title; }

    public String getTitle(){ return title; }

    public void setDetail(String detail){ this.detail = detail; }

    public String getDetail(){ return detail; }

    public void setPicture(String picture){ this.picture = picture; }

    public String getPicture(){ return picture; }

    public void setMoney(float money){ this.money = money; }

    public float getMoney(){ return money; }

    public void setUser(User user){ this.user = user; }

    public User getUser(){ return user; }

    public void setPhone(String phone){ this.phone = phone; }

    public String getPhone(){ return phone; }


    @Override
    public String toString() {
        return "Sale{" +
                "id='" + id + '\'' +
                ", openId='" + openId + '\'' +
                ", title='" + title + '\'' +
                ", detail=" + detail +
                ", picture=" + picture +
                ", money='" + money + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

}
