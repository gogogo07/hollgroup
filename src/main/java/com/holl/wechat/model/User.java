package com.holl.wechat.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "users")
public class User {

    @Id
    @Column(nullable = false)
    private String id;

    @Column()
    private String unionId;

    @Column(nullable = false)
    private String name;

    private Long credit;

    private Integer gender;

    private String language;

    private String city;

    private String province;

    private String country;

    private String avatarUrl;

    public User(){ }

    public User(String id, String unionId, String name, Long credit, Integer gender, String language,
                String city, String province, String country, String avatarUrl) {
        this.id = id;
        this.unionId = unionId;
        this.name = name;
        this.credit = credit;
        this.gender = gender;
        this.language = language;
        this.city = city;
        this.province = province;
        this.country = country;
        this.avatarUrl = avatarUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCredit() {
        return credit;
    }

    public void setCredit(Long credit) {
        this.credit = credit;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
